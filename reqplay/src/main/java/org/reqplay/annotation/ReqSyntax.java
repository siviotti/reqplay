package org.reqplay.annotation;

/**
 * Keywords useds on natural language.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public @interface ReqSyntax {
    
    /** keyword used to identify the user */
    String user();

    /** keyword used to identify the system */
    String system();

}
