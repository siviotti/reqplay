package org.reqplay.model;

import java.util.List;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 19/07/2013
 *
 */
public abstract class ContainerItem extends ReqItem{

    public abstract List<ReqItem> getReferences();
}
