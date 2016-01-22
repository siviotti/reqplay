package org.reqplay.util;

import org.reqplay.model.ReqItem;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 19/07/2013
 *
 */
public class ReqItemConverter {
 
    public String asText(ReqItem reqItem) {
        StringBuilder sb = new StringBuilder();
        sb.append(reqItem.idFormatted());
        sb.append(" popularity:");
        sb.append(reqItem.getPopularity());

        return sb.toString();
    }

}
