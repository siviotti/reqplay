package org.reqplay.text;

import org.reqplay.ReqPlayException;
import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;

/**
 * Token Factory. Creates Tokens from objects.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public class TokenFactory {

    public Token<?> create(Object obj, ReqContext context) {
        if (obj == null){
            throw new ReqPlayException("Cannot create token from null");
        }
        if (obj instanceof Class<?>) {
            ReqItem reqItem = context.getByClass((Class<?>) obj);
            if (reqItem == null) {
                throw new ReqPlayException("None ReqItem for this class:" + obj);
            }
            return new ReqItemToken(reqItem);
        } else if (obj instanceof ReqItem){
            return new ReqItemToken((ReqItem) obj);
        }
        String s = obj.toString();
        return new StringToken(s);
    }
}
