/*
 * Copyright (C)2013 D. Plaindoux.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; see the file COPYING.  If not, write to
 * the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package smallibs.suitcase.cases.xml;

import smallibs.suitcase.cases.genlex.JavaLexer;
import smallibs.suitcase.cases.genlex.Lexer;
import smallibs.suitcase.cases.genlex.TokenRecognizer;
import smallibs.suitcase.cases.genlex.TokenStream;
import smallibs.suitcase.match.Matcher;
import smallibs.suitcase.match.MatchingException;
import smallibs.suitcase.utils.Function;
import smallibs.suitcase.utils.Function2;
import smallibs.suitcase.utils.Function3;
import smallibs.suitcase.utils.Function4;
import smallibs.suitcase.utils.Option;

import static smallibs.suitcase.cases.core.Cases.var;
import static smallibs.suitcase.cases.genlex.Parser.Alt;
import static smallibs.suitcase.cases.genlex.Parser.Ident;
import static smallibs.suitcase.cases.genlex.Parser.Kwd;
import static smallibs.suitcase.cases.genlex.Parser.Opt;
import static smallibs.suitcase.cases.genlex.Parser.Seq;
import static smallibs.suitcase.cases.genlex.Parser.String;
import static smallibs.suitcase.cases.genlex.Parser.parser;
import static smallibs.suitcase.cases.xml.XmlGenLex.Text;
import static smallibs.suitcase.cases.xml.XmlGenLex.TextRecognizer;

public final class Xml {

    // -----------------------------------------------------------------------------------------------------------------
    // JSON lexer definition
    // -----------------------------------------------------------------------------------------------------------------

    static private final Lexer elementLexer;

    static {
        elementLexer = new Lexer();
        elementLexer.skip("\\s+");
        elementLexer.keywords("<?", "<", ">", "</", "/>", "=");
        elementLexer.recognizers(TokenRecognizer.String(), TokenRecognizer.QuotedString(), JavaLexer.IDENT);
    }

    static private final Lexer commentLexer;

    static {
        commentLexer = new Lexer();
        commentLexer.keywords("<!--", "-->");
        commentLexer.recognizers(TextRecognizer(".*?(?=--)"));
    }

    static private final Lexer textLexer;

    static {
        textLexer = new Lexer();
        textLexer.recognizers(TextRecognizer("[^<]+"));
    }

    static private final Lexer cdataLexer;

    static {
        cdataLexer = new Lexer();
        cdataLexer.keywords("<![CDATA[", "]]>");
        cdataLexer.recognizers(TextRecognizer(".*?(?=]]>)"));
    }

    public static TokenStream stream(CharSequence sequence) {
        return elementLexer.parse(sequence);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Xml validation parser
    // -----------------------------------------------------------------------------------------------------------------

    static private final Matcher<TokenStream, Boolean> validator;

    static {
        validator = handleWith(new XmlValidator());
    }

    public static Boolean validate(TokenStream stream) {
        return validator.match(stream);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // XML validator - v0.1
    // -----------------------------------------------------------------------------------------------------------------

    public static <ES, E, AS, A> Matcher<TokenStream, E> handleWith(final XmlHandler<ES, E, AS, A> handler) {
        final Matcher<TokenStream, E> element;
        final Matcher<TokenStream, E> text;
        final Matcher<TokenStream, E> cdata;
        final Matcher<TokenStream, E> comment;
        final Matcher<TokenStream, ES> elements;
        final Matcher<TokenStream, AS> attributes;

        element = parser(Matcher.<TokenStream, E>create(), elementLexer);
        text = parser(Matcher.<TokenStream, E>create(), textLexer);
        cdata = parser(Matcher.<TokenStream, E>create(), cdataLexer);
        comment = parser(Matcher.<TokenStream, E>create(), commentLexer);
        elements = parser(Matcher.<TokenStream, ES>create(), elementLexer);
        attributes = parser(Matcher.<TokenStream, AS>create(), elementLexer);

        element.caseOf(Seq(Kwd("<"), Ident(var), Opt(var.of(attributes)), Kwd("/>"))).then.function(
                new Function2<String, Option<AS>, E>() {
                    @Override
                    public E apply(String name, Option<AS> attributes) throws Exception {
                        return handler.anElement(name, attributes, Option.<ES>None());
                    }
                });

        element.caseOf(Seq(Kwd("<"), Ident(var), Opt(var.of(attributes)), Kwd(">"), Opt(var.of(elements)), Kwd("</"), Ident(var), Kwd(">"))).then.function(
                new Function4<String, Option<AS>, Option<ES>, String, E>() {
                    @Override
                    public E apply(String sname, Option<AS> attributes, Option<ES> content, String ename) throws Exception {
                        if (sname.equals(ename)) {
                            return handler.anElement(sname, attributes, content);
                        } else {
                            throw new MatchingException();
                        }
                    }
                });

        text.caseOf(Text(var)).then.function(
                new Function<String, E>() {
                    @Override
                    public E apply(String cdata) throws Exception {
                        return handler.aText(cdata);
                    }
                });

        cdata.caseOf(Seq(Kwd("<![CDATA["), Text(var), Kwd("]]>"))).then.function(
                new Function<String, E>() {
                    @Override
                    public E apply(String cdata) throws Exception {
                        return handler.aText(cdata);
                    }
                });

        comment.caseOf(Seq(Kwd("<!--"), Text(var), Kwd("-->"))).then.function(
                new Function<String, E>() {
                    @Override
                    public E apply(String cdata) throws Exception {
                        return handler.aComment(cdata);
                    }
                });

        elements.caseOf(Seq(var.of(Alt(comment, cdata, text, element)), Opt(var.of(elements)))).then.function(
                new Function2<E, Option<ES>, ES>() {
                    @Override
                    public ES apply(E element, Option<ES> elements) throws Exception {
                        return handler.someElements(element, elements);
                    }
                }
        );

        attributes.caseOf(Seq(Ident(var), Kwd("="), String(var), Opt(var.of(attributes)))).then.
                function(new Function3<String, String, Option<AS>, AS>() {
                    @Override
                    public AS apply(String name, String value, Option<AS> attribute) throws Exception {
                        return handler.someAttributes(handler.anAttribute(name, value), attribute);
                    }
                });

        return element;
    }
}
