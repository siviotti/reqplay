package org.reqplay.model;

import java.util.ArrayList;
import java.util.List;

import org.reqplay.text.Text;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 * 
 */
public class Condition {
    private List<Object> objects = new ArrayList<Object>();
    private Text text;

	public Condition(Object [] objects) {
		super();
		for (Object o: objects){
		    this.objects.add(o);
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "&&&&&&&&&&&&&&&&&&";
	}

    public String asText() {
        return text.toString();
    }

    public void afterPlay(ReqContext context) {
        text = new Text(objects, context);
    }


}
