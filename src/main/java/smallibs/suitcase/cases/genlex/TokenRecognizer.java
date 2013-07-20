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

package smallibs.suitcase.cases.genlex;

import smallibs.suitcase.utils.Option;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TokenRecognizer {

    public static TokenRecognizer Keyword(String value) {
        return new KeywordRecognizer(value);
    }

    public static TokenRecognizer Ident(String value) {
        return new IdentifierRecognizer(value);
    }

    public static TokenRecognizer String() {
        return new StringRecognizer();
    }

    public static TokenRecognizer Int() {
        return new IntRecognizer();
    }

    public static TokenRecognizer Int(String value) {
        return new IntRecognizer(value);
    }

    public static TokenRecognizer Hexa() {
        return new HexaRecognizer();
    }

    public static Skip Skip(String value) {
        return new Skip(value);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Public abstract methods
    // -----------------------------------------------------------------------------------------------------------------

    public abstract Option<Token<?>> recognize(CharSequence sequence);

    // -----------------------------------------------------------------------------------------------------------------
    // Private classes
    // -----------------------------------------------------------------------------------------------------------------

    private static class KeywordRecognizer extends TokenRecognizer {
        private final String value;

        private KeywordRecognizer(String value) {
            this.value = value;
        }

        @Override
        public Option<Token<?>> recognize(CharSequence sequence) {
            if (value.length() <= sequence.length() && value.contentEquals(sequence.subSequence(0, value.length()))) {
                return new Option.Some<Token<?>>(Token.Keyword(value));
            } else {
                return new Option.None<>();
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static abstract class PatternRecognizer extends TokenRecognizer {
        private final Pattern pattern;

        private PatternRecognizer(String value) {
            this.pattern = Pattern.compile("^" + value);

            // Prevent empty pattern recognition
            if (this.pattern.matcher("").matches()) {
                throw new IllegalArgumentException();
            }
        }

        protected abstract Token<?> matched(String string);

        @Override
        public Option<Token<?>> recognize(CharSequence sequence) {
            final Matcher matcher = pattern.matcher(sequence);
            if (matcher.find()) {
                final CharSequence recognized = sequence.subSequence(matcher.start(), matcher.end());
                return new Option.Some<Token<?>>(matched(recognized.toString()));
            } else {
                return new Option.None<>();
            }
        }
    }

    private static class IdentifierRecognizer extends PatternRecognizer {
        private IdentifierRecognizer(String value) {
            super(value);
        }

        @Override
        protected Token<?> matched(String string) {
            return Token.Ident(string);
        }
    }

    private static class StringRecognizer extends PatternRecognizer {
        private StringRecognizer() {
            super("\"[^\"]*\"");
        }

        @Override
        protected Token<?> matched(String string) {
            return Token.Ident(string.substring(1, string.length() - 1));
        }
    }

    private static class IntRecognizer extends PatternRecognizer {
        private IntRecognizer() {
            this("[+-]?\\d+");
        }

        private IntRecognizer(String value) {
            super(value);
        }

        @Override
        protected Token<?> matched(String string) {
            return Token.Int(string.length(), Integer.valueOf(string));
        }
    }

    private static class HexaRecognizer extends PatternRecognizer {
        private HexaRecognizer() {
            super("0[xX][a-fA-F0-9]+");
        }

        @Override
        protected Token<?> matched(String string) {
            return Token.Int(string.length(), Integer.valueOf(string.substring(2, string.length()), 16));
        }
    }

    public static class Skip extends PatternRecognizer {
        private Skip(String value) {
            super(value);
        }

        @Override
        protected Token<?> matched(String string) {
            return Token.String(string);
        }
    }
}