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

package smallibs.suitcase.cases.xml;

import java.util.Optional;

public class XmlValidator implements XmlHandler<Boolean, Boolean, Boolean, Boolean> {

    @Override
    public Boolean someElements(Boolean element, Optional<Boolean> values) {
        return element && (!values.isPresent() || values.get());
    }

    @Override
    public Boolean anElement(String name, Optional<Boolean> attributes, Optional<Boolean> values) {
        return (!attributes.isPresent() || attributes.get()) && (!values.isPresent() || values.get());
    }

    @Override
    public Boolean aText(String cdata) {
        return true;
    }

    @Override
    public Boolean someAttributes(Boolean attribute, Optional<Boolean> attributes) {
        return attribute && (!attributes.isPresent() || attributes.get());
    }

    @Override
    public Boolean anAttribute(String name, String value) {
        return true;
    }
}
