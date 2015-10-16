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

package smallibs.suitcase.cases.core;

import smallibs.suitcase.cases.Case;
import smallibs.suitcase.cases.MatchResult;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

public class Constant<T> implements Case<T> {

    private final Object object;

    public Constant(T object) {
        this.object = object;
    }

    @Override
    public Optional<MatchResult> unapply(T object) {
        if (object != null && object.equals(this.object)) {
            return Optional.ofNullable(new MatchResult(object));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Class> variableTypes() {
        return new ArrayList<>();
    }
}
