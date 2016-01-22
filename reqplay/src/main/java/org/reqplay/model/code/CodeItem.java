package org.reqplay.model.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reqplay.model.ReqItem;

/**
 * Represents a code element (Class, Method Variable etc).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 22/07/2013
 * 
 */
public abstract class CodeItem<T> {

    private T subject;
    private Map<String, ReqItem> reqItems = new HashMap<String, ReqItem>();
    private CodeItem<?> parent;
    private List<CodeItem<?>> child = new ArrayList<CodeItem<?>>();

    public CodeItem(T subject, CodeItem<?> parent) {
        super();
        this.subject = subject;
        this.parent = parent;
        if (parent != null) {
            parent.child.add(this);
        }
    }

    public void traceTo(ReqItem reqItem) {
        reqItems.put(reqItem.getId(), reqItem);
    }

    public boolean isTracedTo(ReqItem reqItem) {
        return reqItems.containsKey(reqItem.getId());
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getLink(); 

    public T getSubject() {
        return subject;
    }

    public void setSubject(T subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CodeItem<?>) {
            CodeItem<?> codeItem = (CodeItem<?>) obj;
            return getId().equals(codeItem.getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return getId();
    }

    public List<CodeItem<?>> getChildren() {
        return child;
    }

    public CodeItem<?> getParent() {
        return parent;
    }

    public void setParent(CodeItem<?> parent) {
        this.parent = parent;
    }

}
