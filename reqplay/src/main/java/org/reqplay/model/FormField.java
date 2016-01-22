package org.reqplay.model;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 15/07/2013
 * 
 */
public class FormField {

    private String name;
    private Class<?> type;
    private int size;
    private String format;
    private boolean required;
    private String requiredCondition;
    private String[] domain;
    private String obs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getRequiredCondition() {
        return requiredCondition;
    }

    public void setRequiredCondition(String requiredCondition) {
        this.requiredCondition = requiredCondition;
    }

    public String[] getDomain() {
        return domain;
    }

    public void setDomain(String[] domain) {
        this.domain = domain;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FormField) {
            FormField formField = (FormField) obj;
            return name.equals(formField.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name
                + ": "
                + type.getSimpleName()
                + " "
                + size
                + (required ? " required" : "")
                + (format != null && format.length() > 0 ? " (" + format + ")"
                        : "");
    }

}
