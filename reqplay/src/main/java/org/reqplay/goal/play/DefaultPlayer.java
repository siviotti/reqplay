package org.reqplay.goal.play;

import java.lang.reflect.Field;

import java.util.Arrays;
import java.util.List;


import org.reqplay.Executable;
import org.reqplay.ReqPlayException;
import org.reqplay.annotation.Doc;
import org.reqplay.annotation.FieldSpec;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ActorSpec;
import org.reqplay.annotation.element.FeatureSpec;
import org.reqplay.annotation.element.FormSpec;
import org.reqplay.annotation.element.LogEventSpec;
import org.reqplay.annotation.element.MessageSpec;
import org.reqplay.annotation.element.NeedSpec;
import org.reqplay.annotation.element.NonFunctionalSpec;
import org.reqplay.annotation.element.RuleSpec;
import org.reqplay.annotation.element.TermSpec;
import org.reqplay.annotation.element.ViewSpec;
import org.reqplay.annotation.group.Glossary;
import org.reqplay.annotation.group.LogEvents;
import org.reqplay.annotation.group.Messages;
import org.reqplay.model.FormField;
import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.element.Actor;
import org.reqplay.model.element.Feature;
import org.reqplay.model.element.Form;
import org.reqplay.model.element.LogEvent;
import org.reqplay.model.element.Message;
import org.reqplay.model.element.Need;
import org.reqplay.model.element.NonFunctional;
import org.reqplay.model.element.Term;
import org.reqplay.model.element.View;
import org.reqplay.util.ResourceList;

/**
 * Default Player.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public class DefaultPlayer implements Player {   
    private PlayUtil util = new PlayUtil();
    /**
     * Call this method before to use ReqPlay. This method search annoted
     * classes and build the ReqContext instance.
     */
    public void play(ReqContext context) {
        ResourceList resourceList = new ResourceList();
        List<Class<?>> classes = resourceList.listClasses();
        // Create objects from classes
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(ReqItemSpec.class)) {
                addClass(context, clazz, null);
            }
            if (clazz.isAnnotationPresent(Messages.class)) {
                addMessages(context, clazz);
            }
            if (clazz.isAnnotationPresent(Glossary.class)) {
                addTerms(context, clazz);
            }
            if (clazz.isAnnotationPresent(LogEvents.class)) {
                addLogEvents(context, clazz);
            }
        }
        context.afterPlay();
        context.dumpElements();
    }

    private void addLogEvents(ReqContext context, Class<?> clazz) {
        LogEvents logEvents = clazz.getAnnotation(LogEvents.class);
        for (Class<?> c : clazz.getClasses()) {
            if (c.isAnnotationPresent(ReqItemSpec.class)
                    && c.isAnnotationPresent(LogEventSpec.class)) {
                addClass(context, c, null);
            }
        }
    }

    private void addTerms(ReqContext context, Class<?> clazz) {
        Glossary glossary = clazz.getAnnotation(Glossary.class);
        Field[] fields = clazz.getDeclaredFields();
        String s = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TermSpec.class)) {
                TermSpec spec = field.getAnnotation(TermSpec.class);
                try {
                    s = (String) util.getStatic(field, clazz);
                    Term term = new Term();
                    term.setId(spec.id());
                    term.setName(field.getName());
                    term.setDescription(s);
                    term.setEntity(spec.entity());
                    term.setUrl(spec.url());
                    context.put(term);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ReqItem addMessages(ReqContext context, Class<?> clazz) {
        Messages messages = clazz.getAnnotation(Messages.class);
        Field[] fields = clazz.getDeclaredFields();
        String s = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(MessageSpec.class)) {
                MessageSpec spec = field.getAnnotation(MessageSpec.class);
                try {
                    s = (String) field.get(clazz.newInstance());
                    Message message = new Message();
                    message.setId(spec.id());
                    message.setName(field.getName());
                    message.setDescription(s);
                    message.setSeverity(spec.severity());
                    context.put(message);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private ReqItem addClass(ReqContext context, Class<?> clazz, ReqItem parent) {
        if (!clazz.isAnnotationPresent(ReqItemSpec.class)) {
            throw new ReqPlayException(ReqItemSpec.MISSING);
        }
        // ReqItem
        ReqItem reqItem;
        if (Executable.class.isAssignableFrom(clazz)){
            reqItem = util.createExecutable(clazz);
        } else {
            reqItem = createSubClass(context, clazz);    
        }
        util.applySpec(reqItem, clazz);
        util.applyDoc(reqItem, clazz);
        if (parent != null) {
            parent.acceptChild(reqItem);
        }
        context.put(reqItem);
        // Adiciona as subclasses
        for (Class<?> c : clazz.getClasses()) {
            if (c.isAnnotationPresent(ReqItemSpec.class)) {
                addClass(context, c, reqItem);
            }
        }
        return reqItem;
    }


    private ReqItem createSubClass(ReqContext context, Class<?> clazz) {
        ReqItem reqItem = null;
        if (clazz.isAnnotationPresent(NeedSpec.class)) {
            reqItem = createNeed(clazz);
        } else if (clazz.isAnnotationPresent(FeatureSpec.class)) {
            reqItem = createFeature(context, clazz);
        } else if (clazz.isAnnotationPresent(ViewSpec.class)) {
            reqItem = createView(context, clazz);
        } else if (clazz.isAnnotationPresent(ActorSpec.class)) {
            reqItem = createActor(clazz);
        } else if (clazz.isAnnotationPresent(NonFunctionalSpec.class)) {
            reqItem = createNonFunctional(clazz);
        } else if (clazz.isAnnotationPresent(LogEventSpec.class)) {
            reqItem = createLogEvent(clazz);
        } else if (clazz.isAnnotationPresent(FormSpec.class)) {
            reqItem = createForm(clazz);
        } 
        if (reqItem == null) {
            throw new ReqPlayException(
                    "None requirement annotation present at "
                            + clazz.getCanonicalName());
        }
        return reqItem;
    }

    private ReqItem createView(ReqContext context, Class<?> clazz) {
        View view = new View();
        return view;
    }

    private ReqItem createForm(Class<?> clazz) {
        Form form = new Form();
        form.setFormClass(clazz);
        // Fields
        Field[] fields = clazz.getDeclaredFields();
        FormField formField;
        for (Field field : fields) {
            if (!field.isAnnotationPresent(FieldSpec.class)) {
                continue;
            }
            FieldSpec spec = field.getAnnotation(FieldSpec.class);
            formField = new FormField();
            formField.setName(field.getName());
            formField.setType(field.getType());
            formField.setSize(spec.size());
            formField.setFormat(spec.format());
            formField.setRequired(spec.required());
            formField.setRequiredCondition(spec.requiredCondition());
            formField.setDomain(spec.domain());
            formField.setObs(spec.obs());
            form.getFields().add(formField);
        }
        return form;
    }

    private ReqItem createLogEvent(Class<?> clazz) {
        LogEvent logEvent = new LogEvent();

        return logEvent;
    }

    private ReqItem createFeature(ReqContext context, Class<?> clazz) {
        FeatureSpec spec = clazz.getAnnotation(FeatureSpec.class);
        Feature feature = new Feature();
        List<Class<?>> list = Arrays.asList(spec.actor());
        ReqItem child;
        for (Class<?> c : list) {
            if (!c.isAnnotationPresent(ActorSpec.class)) {
                throw new ReqPlayException("ActorSpec missing in " + c);
            }
            feature.getActorClasses().add(c);
        }
        return feature;
    }

    private ReqItem createNeed(Class<?> clazz) {
        NeedSpec spec = clazz.getAnnotation(NeedSpec.class);
        Need need = new Need();
        need.setBenefit(spec.benefit());
        return need;
    }

    private ReqItem createNonFunctional(Class<?> clazz) {
        NonFunctional nonFunctional = new NonFunctional();
        NonFunctionalSpec spec = (NonFunctionalSpec) clazz
                .getAnnotation(NonFunctionalSpec.class);
        nonFunctional.setDescription(spec.value());
        return nonFunctional;
    }

    private ReqItem createActor(Class<?> clazz) {
        Actor actor = new Actor();
        ActorSpec spec = (ActorSpec) clazz.getAnnotation(ActorSpec.class);
        actor.setRole(spec.role());
        actor.setInputs(spec.inputs());
        actor.setRepresentative(spec.representative());
        return actor;
    }

}
