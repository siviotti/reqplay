package org.reqplay.text;

/**
 * Token for Strings.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class StringToken extends Token<String>{

    public StringToken(String subject) {
        super(subject);
    }

    @Override
    public String asText() {
        return subject;
    }

}
