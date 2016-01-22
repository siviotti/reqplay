package org.reqplay.model.element;

import java.util.ArrayList;
import java.util.List;

import org.reqplay.Executable;
import org.reqplay.ReqPlayException;
import org.reqplay.annotation.element.RuleSpec;
import org.reqplay.model.ContainerItem;
import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;
import org.reqplay.text.ReqItemToken;
import org.reqplay.text.Text;
import org.reqplay.text.Token;

/**
 * Represents the Business Rules in the system.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class Rule extends ContainerItem implements Executable {

    private List<Object> objects = new ArrayList<Object>();
    private Text text;

    public Rule() {
        super();
        setType(ReqType.RULE.name);
        spec();
    }

    public void spec() {

    }

    @Override
    public void afterPlay(ReqContext context) {
        super.afterPlay(context);
        RuleSpec spec = getClass().getAnnotation(RuleSpec.class);
        if (spec == null) {
            throw new ReqPlayException(
                    "Rules must be annotated with @RuleSpec: "
                            + getClass().getCanonicalName());
        }
        if (spec.value().length() > 0) {
            objects.add(spec.value());
        }
        text = new Text(objects, context);
        setDescription(text.toString());
    }

    /**
     * Add a text (list of objects).
     * 
     * @param objects
     *            The onject list.
     * @return this instance.
     */
    protected Rule _(Object... objects) {
        for (Object o : objects) {
            this.objects.add(o);
        }
        return this;
    }

    /**
     * New line.
     * 
     * @param objects
     * @return
     */
    protected Rule $(Object... objects) {
        this.objects.add("\n");
        _(objects);
        return this;
    }

    /**
     * Tab.
     * 
     * @param objects
     *            The object List.
     * @return this instance.
     */
    protected Rule $$(Object... objects) {
        this.objects.add("\n@");
        _(objects);
        return this;
    }

    // ******************** GET / SET ********************

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public String asText() {
        return super.asText().concat(" - ").concat(text.toString());
    }

    public List<ReqItem> getReferences() {
        return text.getReqItems();
    }

}
