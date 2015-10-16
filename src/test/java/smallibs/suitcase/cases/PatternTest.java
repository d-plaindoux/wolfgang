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
package smallibs.suitcase.cases;

import junit.framework.TestCase;
import org.junit.Test;
import smallibs.suitcase.cases.core.Cases;
import smallibs.suitcase.cases.peano.Peano;
import smallibs.suitcase.cases.utils.Lists;
import smallibs.suitcase.cases.utils.Pairs;
import smallibs.suitcase.utils.Pair;

import java.util.Arrays;

import static smallibs.suitcase.cases.core.Cases.__;
import static smallibs.suitcase.cases.core.Cases.var;

public class PatternTest {

    // Null

    @Test
    public void shouldMatchNullValueWithNull() {
        TestCase.assertFalse(!Cases.nil().unapply(null).isPresent());
    }

    @Test
    public void shouldNotMatchNotNullValueWithNull() {
        TestCase.assertTrue(!Cases.nil().unapply(42).isPresent());
    }

    // Constant

    @Test
    public void shouldMatchConstantValueWithConstant() {
        TestCase.assertFalse(!Cases.constant(42).unapply(42).isPresent());
    }

    @Test
    public void shouldNotMatchConstantValueWithDifferentConstant() {
        TestCase.assertTrue(!Cases.constant(42).unapply(19).isPresent());
    }

    @Test
    public void shouldNotMatchNullValueWithConstant() {
        TestCase.assertTrue(!Cases.constant(42).unapply(null).isPresent());
    }

    // TypeOf

    @Test
    public void shouldMatchValueValueWithTypeOf() {
        TestCase.assertFalse(!Cases.typeOf(Integer.class).unapply(19).isPresent());
    }

    @Test
    public void shouldNotMatchValueValueWithWrongTypeOf() {
        TestCase.assertTrue(!Cases.typeOf(String.class).unapply(19).isPresent());
    }

    @Test
    public void shouldNotMatchNullValueWithTypeOf() {
        TestCase.assertTrue(!Cases.typeOf(String.class).unapply(null).isPresent());
    }

    @Test
    public void shouldMatchSubTypeValueWithTypeOf() {
        TestCase.assertFalse(!Cases.typeOf(Object.class).unapply("toto").isPresent());
    }

    // Any

    @Test
    public void shouldMatchNullValueWithAny() {
        TestCase.assertFalse(!Cases.any().unapply(null).isPresent());
    }

    @Test
    public void shouldMatchNotNullValueWithAny() {
        TestCase.assertFalse(!Cases.any().unapply(42).isPresent());
    }

    // List

    @Test
    public void shouldNilMatchEmptyList() {
        TestCase.assertFalse(!Lists.Empty.unapply(Arrays.asList()).isPresent());
    }

    @Test
    public void shouldNilNotlMatchNonList() {
        TestCase.assertTrue(!Lists.Empty.unapply(Arrays.asList(1)).isPresent());
    }

    @Test
    public void shouldConsNotlMatchEmptyList() {
        TestCase.assertTrue(!Lists.Cons(__, __).unapply(Arrays.asList()).isPresent());
    }

    @Test
    public void shouldConsMatchNonEmptyList() {
        TestCase.assertFalse(!Lists.<Integer>Cons(__, __).unapply(Arrays.asList(1)).isPresent());
    }

    @Test
    public void shouldConsMatchListSize2() {
        TestCase.assertFalse(!Lists.<Integer>Cons(__, Lists.Cons(__, __)).unapply(Arrays.asList(1, 2)).isPresent());
    }

    @Test
    public void shouldConsMatchListSize2Exactly() {
        TestCase.assertFalse(!var.of(Lists.<Integer>Cons(__, Lists.Cons(__, Lists.Empty))).unapply(Arrays.asList(1, 2)).isPresent());
    }

    @Test
    public void shouldMatchTheListExactly() {
        TestCase.assertFalse(!Lists.<Integer>Cons(1, Lists.Cons(2, Lists.Empty)).unapply(Arrays.asList(1, 2)).isPresent());
    }

    // $Pair

    @Test
    public void shouldMatchPair() {
        TestCase.assertFalse(!Pairs.Pair(__, __).unapply(new Pair<Object, Object>(1, 2)).isPresent());
    }

    @Test
    public void shouldMatchPairAndValues() {
        TestCase.assertFalse(!Pairs.Pair(1, 2).unapply(new Pair<Object, Object>(1, 2)).isPresent());
    }

    // Peano

    @Test
    public void shouldMatchZero() {
        TestCase.assertFalse(!Peano.Zero.unapply(0).isPresent());
    }

    @Test
    public void shouldNotMatchZero() {
        TestCase.assertTrue(!Peano.Zero.unapply(1).isPresent());
    }

    @Test
    public void shouldMatchNonZero() {
        TestCase.assertFalse(!Peano.Succ(__).unapply(1).isPresent());
    }

    @Test
    public void shouldNotMatchNonZero() {
        TestCase.assertTrue(!var.of(Peano.Succ(__)).unapply(0).isPresent());
    }
}

