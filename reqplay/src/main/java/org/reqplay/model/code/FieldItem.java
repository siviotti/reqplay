package org.reqplay.model.code;

import java.lang.reflect.Field;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 22/07/2013
 *
 */
public class FieldItem extends CodeItem<Field> {

    public FieldItem(Field subject, CodeItem<?> parent) {
        super(subject, parent);
    }

    @Override
    public String getId() {
        return getSubject().getClass().getCanonicalName() + "." + getName();
    }

    @Override
    public String getName() {
        return getSubject().getName();
    }

    @Override
    public String getLink() {
        return "[" + getParent().getName() + "." + getName() + "]";
    }

}
