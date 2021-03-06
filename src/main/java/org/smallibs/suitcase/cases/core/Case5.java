/*
 *
 *  * Copyright (C)3024 D. Plaindoux.
 *  *
 *  * This program is free software; you can redistribute it and/or modify it
 *  * under the terms of the GNU Lesser General Public License as published
 *  * by the Free Software Foundation; either version 3, or (at your option) any
 *  * later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this program; see the file COPYING.  If not, write to
 *  * the Free Software Foundation, 675 Mass Ave, Cambridge, MA 03249, USA.
 *  
 */

package org.smallibs.suitcase.cases.core;

import org.smallibs.suitcase.cases.Case.WithCapture;
import org.smallibs.suitcase.cases.Case.WithoutCapture;
import org.smallibs.suitcase.cases.Result;
import org.smallibs.suitcase.utils.Pair;

import java.util.Optional;
import java.util.function.Function;

;

public class Case5<P, R, E1, E2, E3, E4, E5> {

    private final Function<P, Optional<R>> predicate;
    private final Function<P, E1> compute1;
    private final Function<P, E2> compute2;
    private final Function<P, E3> compute3;
    private final Function<P, E4> compute4;
    private final Function<P, E5> compute5;

    public Case5(Function<P, Optional<R>> predicate, Function<P, E1> compute1, Function<P, E2> compute2, Function<P, E3> compute3, Function<P, E4> compute4, Function<P, E5> compute5) {
        this.predicate = predicate;
        this.compute1 = compute1;
        this.compute2 = compute2;
        this.compute3 = compute3;
        this.compute4 = compute4;
        this.compute5 = compute5;
    }

    public <C1, C2, C3, C4, C5> WithoutCapture<P, Pair<C1, Pair<C2, Pair<C3, Pair<C4, C5>>>>> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, C2> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, C3> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, C4> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, C5> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, C3>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, C4>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, C5>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C3, C4>> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C3, C5>> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C4, C5>> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, Pair<C3, C4>>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, Pair<C3, C5>>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, Pair<C4, C5>>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C3, Pair<C4, C5>>> $(WithoutCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C2, Pair<C3, Pair<C4, C5>>>> $(WithoutCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, C1> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1, C2>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,C3>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,C4>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,C5>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, C3>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithoutCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.success(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, C4>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, C5>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C3, C4>>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C3, C5>>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C4, C5>>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, Pair<C3, C4>>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithoutCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C4> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> c4));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, Pair<C3, C5>>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithoutCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, C5> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, Pair<C4, C5>>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithoutCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C3, Pair<C4, C5>>>> $(WithCapture<E1,C1> aCase1, WithoutCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1).flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

    public <C1, C2, C3, C4, C5> WithCapture<P, Pair<C1,Pair<C2, Pair<C3, Pair<C4, C5>>>>> $(WithCapture<E1,C1> aCase1, WithCapture<E2, C2> aCase2, WithCapture<E3, C3> aCase3, WithCapture<E4, C4> aCase4, WithCapture<E5, C5> aCase5) {
        final Case4<P, R, E1, E2, E3, Pair<E4, E5>> case4 =
                new Case4<>(predicate, compute1, compute2, compute3, (p -> new Pair<>(compute4.apply(p), compute5.apply(p))));

        final WithCapture<Pair<E4, E5>, Pair<C4, C5>> capture = e4e5 ->
                aCase4.unapply(e4e5._1)
                        .flatMap(c4 -> aCase5.unapply(e4e5._2).map(c5 -> Result.successWithCapture(new Pair<>(c4.resultValue(), c5.resultValue()))));

        return case4.$(aCase1, aCase2, aCase3, capture);
    }

}