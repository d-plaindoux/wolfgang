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

package smallibs.suitcase.cases.json;

import java.util.Optional;
import smallibs.suitcase.utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POJOBuilder implements JSonHandler<Object, Map<String, Object>, Pair<String, Object>, List<Object>, Object> {
    @Override
    public Object anObject(Optional<Map<String, Object>> members) {
        if (!members.isPresent()) {
            return new HashMap<>();
        } else {
            return members.get();
        }
    }

    @Override
    public Object anArray(Optional<List<Object>> values) {
        if (!values.isPresent()) {
            return new ArrayList<>();
        } else {
            return values.get();
        }
    }

    @Override
    public Map<String, Object> someMembers(Pair<String, Object> o1, Optional<Map<String, Object>> o2) {
        final Map<String, Object> objectMap;
        if (!o2.isPresent()) {
            objectMap = new HashMap<>();
        } else {
            objectMap = o2.get();
        }

        objectMap.put(o1._1, o1._2);

        return objectMap;
    }

    @Override
    public Pair<String, Object> aMember(String o1, Object o2) {
        return new Pair<>(o1, o2);
    }

    @Override
    public List<Object> someValues(Object o1, Optional<List<Object>> o2) {
        final List<Object> objectList;
        if (!o2.isPresent()) {
            objectList = new ArrayList<>();
        } else {
            objectList = o2.get();
        }

        objectList.add(0, o1);

        return objectList;
    }

    @Override
    public Object anInteger(int i) {
        return i;
    }

    @Override
    public Object aString(String s) {
        return s;
    }

    @Override
    public Object aFloat(float f) {
        return f;
    }

    @Override
    public Object aNull() {
        return null;
    }

    @Override
    public Object aBoolean(boolean b) {
        return b;
    }

    @Override
    public Object aValue(Object o) {
        return o;
    }
}
