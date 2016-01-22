package org.reqplay.model;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public enum ReqType {

	// Functionals
    /** */
    NEED("Need"),
    /** */
    FEATURE("Feature"),
    /** */
    VIEW("View"),
    /** */
    SCENARIO("Scenario"),
    /** */
    ACTOR("Actor"),
    /** */
    MESSAGE("Message"),
    // Domain
    /** */
    RULE("Rule"),
    /** */
    TERM("Term"),
    /** */
    FORM("Form"),
    // Non-functionals
    /** */
    NONFUNCTIONAL("Non-functional"),
    /** */
    LOGEVENT("Log Event")    
    ;

    public final String name;

    private ReqType(String name) {
        this.name = name;
    }

}
