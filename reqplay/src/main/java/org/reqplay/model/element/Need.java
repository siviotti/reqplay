package org.reqplay.model.element;

import org.reqplay.model.Benefit;
import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 14/07/2013
 * 
 */
public class Need extends ReqItem {

    private Benefit benefit;

    public Need() {
        super();
        setType(ReqType.NEED.name);
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder(super.asText());
        if (!getChildren().isEmpty()) {
            sb.append(" - Features:");
            for (ReqItem reqItem : getChildren()) {
                sb.append(reqItem.link());
            }
        }
        return sb.toString();
    }
}
