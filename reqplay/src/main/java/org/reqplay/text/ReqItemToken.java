package org.reqplay.text;

import org.reqplay.model.ReqItem;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 *
 */
public class ReqItemToken extends Token<ReqItem>{

    public ReqItemToken(ReqItem subject) {
        super(subject);
    }

    @Override
    public String asText() {
        return subject.link();
    }

}
