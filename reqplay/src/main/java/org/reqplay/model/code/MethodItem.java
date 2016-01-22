package org.reqplay.model.code;

import java.lang.reflect.Method;

/**
 * Method Item.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 22/07/2013
 * 
 */
public class MethodItem extends CodeItem<Method> {

    public MethodItem(Method subject, CodeItem<?> parent) {
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
