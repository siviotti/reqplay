package org.reqplay.text;

/**
 * Represents a part of a statement.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public abstract class Token<T> {

    protected T subject;

    public Token(T subject) {
        super();
        this.subject = subject;
    }

    public abstract String asText();

    // ******************** GET / SET ********************

    public T getSubject() {
        return subject;
    }

    public void setSubject(T subject) {
        this.subject = subject;
    }


}
