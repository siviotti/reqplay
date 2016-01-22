package org.reqplay.model.element;

import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 15/07/2013
 * 
 */
public class Term extends ReqItem{
    
    private Class<?> entity;
    private String url;

	public Term() {
		super();
		setType(ReqType.TERM.name);
	}

	
    @Override
    public String asText() {
        return super.asText().concat(" - ").concat(getDescription());
    }


    @Override
    public String link() {
        return "[".concat(getName()).concat("]");
    }


    public Class<?> getEntity() {
        return entity;
    }


    public void setEntity(Class<?> entity) {
        this.entity = entity;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

}
