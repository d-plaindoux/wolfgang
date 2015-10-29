/*
 * Copyright (C)2015 D. Plaindoux.
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

package org.smallibs.suitcase.match;

import junit.framework.TestCase;
import org.junit.Test;
import org.smallibs.suitcase.cases.utils.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.smallibs.suitcase.cases.core.Cases.__;
import static org.smallibs.suitcase.cases.core.Cases.var;
import static org.smallibs.suitcase.cases.utils.Lists.Cons;
import static org.smallibs.suitcase.cases.utils.Lists.Empty;

public class ListMatcherTest {
    @Test
    public void shouldMatchLisContainingAnObject() throws MatchingException {
        final Matcher<List<Object>, Boolean> isEmpty = Matcher.create();

        isEmpty.caseOf(Cons(1, __)).then(true);
        isEmpty.caseOf(__).then(false);

        TestCase.assertTrue(isEmpty.match(Collections.singletonList(1)));
        TestCase.assertFalse(isEmpty.match(Collections.emptyList()));
        TestCase.assertFalse(isEmpty.match(Arrays.asList(2, 3)));
    }

    @Test
    public void shouldComputeListSizeWithAdHocPatternObject() throws MatchingException {
        final Matcher<List<?>, Integer> sizeOfMatcher = Matcher.create();

        sizeOfMatcher.caseOf(Empty()).then(0);
        sizeOfMatcher.caseOf(Cons(__, var)).then((List<Object> tail) -> 1 + sizeOfMatcher.match(tail));

        TestCase.assertEquals(0, sizeOfMatcher.match(Collections.emptyList()).intValue());
        TestCase.assertEquals(4, sizeOfMatcher.match(Arrays.asList(1, 2, 3, 4)).intValue());
    }

    @Test
    public void shouldComputeAdditionWithAdHocPatternObject() throws MatchingException {
        final Matcher<List<Integer>, Integer> addAll = Matcher.create();

        addAll.caseOf(Empty()).then(0);
        addAll.caseOf(Cons(var, var)).then((Integer i, List<Integer> l) -> i + addAll.match(l));

        TestCase.assertEquals(0, addAll.match(Collections.emptyList()).intValue());
        TestCase.assertEquals(10, addAll.match(Arrays.asList(1, 2, 3, 4)).intValue());
    }

    @Test
    public void shouldCheckIntegerAsHeadListAndReturnImplicitConstantValue() throws MatchingException {
        final Matcher<List<Integer>, Boolean> headIsZero = Matcher.create();

        headIsZero.caseOf(Cons(0, __)).then(true);
        headIsZero.caseOf(__).then(false);

        TestCase.assertTrue(headIsZero.match(Collections.singletonList(0)));
        TestCase.assertFalse(headIsZero.match(Arrays.asList(1, 2)));
    }
}
