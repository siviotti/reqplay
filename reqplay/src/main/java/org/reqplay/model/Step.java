package org.reqplay.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.reqplay.text.Text;

/**
 * Represents a single step ...
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class Step {

    private String id;
    private int index;
    // Attributes before play
    private List<Object> objects = new ArrayList<Object>();

    private Class<?> viewClass;
    private Class<?> includedClass;
    private Class<?> transactionClass;

    // Attributes after play
    private Text text;

    public Step(String id, Object[] objects) {
        super();
        this.id = id;
        for (Object obj : objects) {
            this.objects.add(obj);
        }
    }

    public String asText() {
        return "    " + id + " - " + text.toString();
    }

    public void afterPlay(ReqContext context) {
        ReqItem reqItem;
        if (viewClass != null && context.containsByClass(viewClass)) {
            reqItem = context.getByClass(viewClass);
            reqItem.afterPlay(context);
            add(reqItem);
        }
        if (includedClass != null && context.containsByClass(includedClass)) {
            reqItem = context.getByClass(includedClass);
            reqItem.afterPlay(context);
            add("Include ");
            add(reqItem);
        }
        if (transactionClass != null
                && context.containsByClass(transactionClass)) {
            reqItem = context.getByClass(transactionClass);
            reqItem.afterPlay(context);
            add(reqItem);
        }
        text = new Text(objects, context);
    }

    public void add(Object object) {
        objects.add(object);
    }

    // ******************** GET / SET ********************

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Class<?> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<?> viewClass) {
        this.viewClass = viewClass;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public Class<?> getIncludedClass() {
        return includedClass;
    }

    public void setIncludedClass(Class<?> includedClass) {
        this.includedClass = includedClass;
    }

    public Class<?> getTransactionClass() {
        return transactionClass;
    }

    public void setTransactionClass(Class<?> transactionClass) {
        this.transactionClass = transactionClass;
    }

    @Override
    public String toString() {
        return id;
    }

    public List<ReqItem> getReferences() {
        return text.getReqItems();
    }

}
