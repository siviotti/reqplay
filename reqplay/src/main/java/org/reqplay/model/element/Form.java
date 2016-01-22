package org.reqplay.model.element;

import java.util.ArrayList;
import java.util.List;

import org.reqplay.model.FormField;
import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 15/07/2013
 *
 */
public class Form extends ReqItem{
    
    private Class<?> formClass;
    private List<FormField> fields = new ArrayList<FormField>();

    public Form() {
        super();
        setType(ReqType.FORM.name);
    }

    public Class<?> getFormClass() {
        return formClass;
    }

    public void setFormClass(Class<?> formClass) {
        this.formClass = formClass;
    }

    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> fields) {
        this.fields = fields;
    }

}
