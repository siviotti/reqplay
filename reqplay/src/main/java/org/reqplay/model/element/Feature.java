package org.reqplay.model.element;

import java.util.ArrayList;
import java.util.List;

import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * Represents a Functional Component.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public class Feature extends ReqItem {

    private List<Actor> actors = new ArrayList<Actor>();
    private List<Class<?>> actorClasses = new ArrayList<Class<?>>();

    public Feature() {
        super();
        setType(ReqType.FEATURE.name);
    }

    @Override
    public void afterPlay(ReqContext context) {
        super.afterPlay(context);
        actors.clear();
        for (Class<?> clazz : actorClasses) {
            actors.add((Actor) context.getByClass(clazz));
        }
    }

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder(super.asText());
        if (!actors.isEmpty()) {
            sb.append(" - Actors:");
            for (Actor actor : actors) {
                sb.append(actor.link());
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    // GET / SET
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Class<?>> getActorClasses() {
        return actorClasses;
    }

    public void setActorClasses(List<Class<?>> actorClasses) {
        this.actorClasses = actorClasses;
    }

}
