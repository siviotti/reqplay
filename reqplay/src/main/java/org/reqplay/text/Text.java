package org.reqplay.text;

import java.util.ArrayList;
import java.util.List;

import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;

/**
 * List of <code>Token</code>.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/07/2013
 * 
 */
public class Text {

    private List<Token<?>> tokens = new ArrayList<Token<?>>();
    private Text next;
    private ReqContext context;

    public Text(List<Object> objects, ReqContext context) {
        this.context = context;
        TokenFactory factory = new TokenFactory();
        for (Object o : objects) {
            if (o != null) {
                tokens.add(factory.create(o, context));
            }
        }
    }

    public List<Token<?>> getTokens() {
        return tokens;
    }

    public Text getNext() {
        return next;
    }

    public void setNext(Text next) {
        this.next = next;
    }

    public List<ReqItem> getReqItems() {
        List<ReqItem> list = new ArrayList<ReqItem>();
        String s;
        for (Token<?> token : getTokens()) {
            if (token instanceof ReqItemToken) {
                list.add((ReqItem) token.getSubject());
            } else if (token instanceof StringToken) {
                s = token.getSubject().toString();
                if (context.getMessages().contains(s)) {
                    list.add(context.getMessages().get(s));
                } 
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token<?> token : tokens) {
            sb.append(token.asText());
        }
        if (next != null) {
            sb.append(next.toString());
        }
        return sb.toString();
    }

}
