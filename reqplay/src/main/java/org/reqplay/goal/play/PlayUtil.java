package org.reqplay.goal.play;

import java.lang.reflect.Field;

import org.reqplay.ReqPlayException;
import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.RuleSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.ReqItem;
import org.reqplay.model.element.Rule;
import org.reqplay.model.element.scenario.Scenario;

/**
 * Utility class for the goal Play.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 15/07/2013
 * 
 */
public class PlayUtil {

    public ReqItem createExecutable(Class<?> clazz) {
        try {
            return (ReqItem) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        }
    }

    public void applySpec(ReqItem reqItem, Class<?> clazz) {
        ReqItemSpec spec = (ReqItemSpec) clazz.getAnnotation(ReqItemSpec.class);
        reqItem.setId(spec.id());
        reqItem.setName(spec.name());
        reqItem.setClassName(getClass().getCanonicalName());
        // Defined
        reqItem.setPriority(spec.priority());
        reqItem.setComplexity(spec.complexity());
        reqItem.setRisk(spec.risk());
        reqItem.setSize(spec.size());
    }

    public void applyDoc(ReqItem reqItem, Class<?> clazz) {
        if (clazz.isAnnotationPresent(Doc.class)) {
            Doc doc = clazz.getAnnotation(Doc.class);
            reqItem.setAuthor(doc.author());
            reqItem.setVersion(doc.version());
            reqItem.setSystem(doc.system());
            reqItem.setProject(doc.project());
            reqItem.setArtifact(doc.artifact());
        }
    }

    public void applyScenario() {

    }

    public Object getStatic(Field field, Class<?> clazz) {
        try {
            return field.get(clazz.newInstance());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        }
    }

  

}
