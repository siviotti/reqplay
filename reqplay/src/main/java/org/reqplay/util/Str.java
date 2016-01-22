package org.reqplay.util;

public final class Str {
    
    private Str(){
        
    }
    
    public static String fill(final String baseText, final char ch,
            final int size) {
        String text = baseText;
        if (text == null) {
            text = "";
        }
        StringBuffer sb = new StringBuffer(text);
        for (int i = text.length(); i < size; i++) {
            sb.append("" + ch);
        }
        return sb.toString();
    }

    public static String fill(String text, int size) {
        return fill(text, ' ', size);
    }
}
