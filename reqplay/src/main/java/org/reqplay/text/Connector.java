package org.reqplay.text;

import java.util.ResourceBundle;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 09/06/2013
 * 
 */
public final class Connector {
	private static ResourceBundle bundle = ResourceBundle.getBundle("reqplay");
	
	public static final String AND = bundle.getString("reqplay.connector.and");
	public static final String OR = bundle.getString("reqplay.connector.or");
	
	
	
	
}
