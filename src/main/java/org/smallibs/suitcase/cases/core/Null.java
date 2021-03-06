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

package org.smallibs.suitcase.cases.core;

import org.smallibs.suitcase.cases.Case;
import org.smallibs.suitcase.cases.Result;

import java.util.Optional;

class Null<T> implements Case.WithoutCapture<T, T> {

    Null() {
        // Nothing
    }

    @Override
    public Optional<Result.WithoutCapture<T>> unapply(T object) {
        if (object == null) {
            return Optional.of(Result.success(object));
        } else {
            return Optional.empty();
        }
    }

}
