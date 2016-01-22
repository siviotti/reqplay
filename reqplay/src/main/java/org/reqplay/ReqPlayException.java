package org.reqplay;

/**
 * General exception to ReqPlay framework.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class ReqPlayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReqPlayException() {
        super();
    }

    public ReqPlayException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReqPlayException(String message) {
        super(message);
    }

    public ReqPlayException(Throwable cause) {
        super(cause);
    }

}
