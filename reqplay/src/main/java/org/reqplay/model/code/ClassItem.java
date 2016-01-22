package org.reqplay.model.code;


/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 22/07/2013
 *
 */
public class ClassItem extends CodeItem<Class<?>>{

    public ClassItem(Class<?> subject, CodeItem<?> parent) {
        super(subject, parent);
    }

    @Override
    public String getId() {
        return getSubject().getCanonicalName();
    }

    @Override
    public String getName() {
        return getSubject().getSimpleName();
    }

    @Override
    public String getLink() {
        return "[" + getName() + "]";
    }


}
