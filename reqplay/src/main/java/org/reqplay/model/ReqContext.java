package org.reqplay.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reqplay.Executable;
import org.reqplay.ReqPlayException;
import org.reqplay.annotation.ReqContextSpec;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.goal.compile.ReqCompiler;
import org.reqplay.goal.decompile.Decompiler;
import org.reqplay.goal.eval.Evaluator;
import org.reqplay.goal.eval.analize.Analizer;
import org.reqplay.goal.eval.estimative.Estimator;
import org.reqplay.goal.eval.rate.Rater;
import org.reqplay.goal.eval.trace.Tracer;
import org.reqplay.goal.eval.validation.ReqValidator;
import org.reqplay.goal.play.Player;
import org.reqplay.goal.recode.Recoder;
import org.reqplay.model.element.Actor;
import org.reqplay.model.element.Feature;
import org.reqplay.model.element.Message;
import org.reqplay.model.element.Term;
import org.reqplay.util.ReqItemConverter;
import org.reqplay.util.Str;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/06/2013
 * 
 */
public class ReqContext {

    private Map<String, ReqItem> items = new HashMap<String, ReqItem>();
    private ContextGlossary glossary = new ContextGlossary();
    private ContextMessages messages = new ContextMessages();
    private Player player;
    private Evaluator evaluator;
    private Tracer tracer;
    private Estimator estimator;
    private Recoder recoder;
    private ReqCompiler compiler;
    private Decompiler decompiler;
    private Analizer analizer;
    private Rater rater;
    private ReqValidator validator;
    private int idFormatSize;

    public ReqContext() {
        ReqContextSpec spec = getClass().getAnnotation(ReqContextSpec.class);
        try {
            player = spec.player().newInstance();
            evaluator = spec.evaluator().newInstance();
            tracer = spec.tracer().newInstance();
            estimator = spec.estimator().newInstance();
            recoder = spec.recoder().newInstance();
            compiler = spec.compiler().newInstance();
            decompiler = spec.decompiler().newInstance();
            analizer = spec.analizer().newInstance();
            rater = spec.rater().newInstance();
            validator = spec.validator().newInstance();
            idFormatSize = spec.idFormatSize();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ReqPlayException(e);
        }
    }

    public ReqContext load() {
        player.play(this);
        evaluator.eval(this, tracer, estimator, analizer, rater, validator);
        return this;
    }

    public ReqContext save() {
        recoder.recode(this);
        return this;
    }

    // ******************** Items Methods ********************

    public ReqItem put(ReqItem item) {
        if (item instanceof Term) {
            glossary.put((Term) item);
        }
        if (item instanceof Message) {
            messages.put((Message) item);
        }
        String id = item.getId();
        if (items.containsKey(id)) {
            ReqItem oldItem = get(id);
            oldItem.exceptionDuplicatedId();
            throw item.exceptionDuplicatedId();
        }
        items.put(item.getId(), item);
        return item;
    }

    public boolean contains(String id) {
        return items.containsKey(id);
    }

    public boolean containsByClass(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(ReqItemSpec.class)) {
            throw new ReqPlayException(ReqItemSpec.MISSING);
        }
        ReqItemSpec spec = clazz.getAnnotation(ReqItemSpec.class);
        return contains(spec.id());
    }

    public void remove(String id) {
        items.remove(id);
    }

    public Set<String> idSet() {
        return items.keySet();
    }

    public List<ReqItem> getItems() {
        return new ArrayList<ReqItem>(items.values());
    }

    public List<ContainerItem> getContainerItems() {
        List<ContainerItem> list = new ArrayList<ContainerItem>();
        for (ReqItem reqItem : items.values()) {
            if (reqItem instanceof ContainerItem) {
                list.add((ContainerItem) reqItem);
            }
        }
        return list;
    }

    public ReqItem get(String id) {
        return items.get(id);
    }

    public ReqItem getByClass(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(ReqItemSpec.class)) {
            throw new ReqPlayException(ReqItemSpec.MISSING);
        }
        ReqItemSpec spec = clazz.getAnnotation(ReqItemSpec.class);
        String id = spec.id();
        return get(spec.id());
    }

    /**
     * Create instances from classes in all items.
     */
    public void afterPlay() {
        for (ReqItem reqItem : items.values()) {
            reqItem.afterPlay(this);
        }
    }

    // ******************** DUMPS ********************

    public void dumpElements() {
        System.out.println("*** ELEMENTS DUMP ***");
        List<ReqItem> list = new ArrayList<ReqItem>(items.values());
        Collections.sort(list);
        for (ReqItem reqItem : list) {
            System.out.println(reqItem.asText());
        }
        // Sumary
        System.out.println("ReqItems:" + items.size());
        System.out.println("Glossary:" + glossary.getTerms());
        System.out.println("Messages:" + messages.getMessages());
    }

    public void dumpAttributes() {
        ReqItemConverter converter = new ReqItemConverter();
        System.out.println("*** ATTRIBUTES DUMP ***");
        List<ReqItem> list = new ArrayList<ReqItem>(items.values());
        Collections.sort(list);
        for (ReqItem reqItem : list) {
            System.out.println(converter.asText(reqItem));
        }
    }

    public void dumpTraceability() {
        System.out.println("*** TRACEABILITY DUMP ***");
        List<ReqItem> list = new ArrayList<ReqItem>(items.values());
        Collections.sort(list);
        for (ReqItem reqItem : list) {
            System.out.println(reqItem.idFormatted() + " traced by:"
                    + reqItem.tracedByStr());
            System.out.println(Str.fill("", idFormatSize) + "  trace to:"
                    + reqItem.traceToStr());
        }
    }

    // ******************** GET / SET ********************

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Recoder getRecoder() {
        return recoder;
    }

    public void setRecoder(Recoder recoder) {
        this.recoder = recoder;
    }

    public Tracer getTracer() {
        return tracer;
    }

    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    public Estimator getEstimator() {
        return estimator;
    }

    public void setEstimator(Estimator estimator) {
        this.estimator = estimator;
    }

    public ReqCompiler getCompiler() {
        return compiler;
    }

    public void setCompiler(ReqCompiler compiler) {
        this.compiler = compiler;
    }

    public Decompiler getDecompiler() {
        return decompiler;
    }

    public void setDecompiler(Decompiler decompiler) {
        this.decompiler = decompiler;
    }

    public Analizer getAnalizer() {
        return analizer;
    }

    public void setAnalizer(Analizer analizer) {
        this.analizer = analizer;
    }

    public ReqValidator getValidator() {
        return validator;
    }

    public void setValidator(ReqValidator validator) {
        this.validator = validator;
    }

    public ContextGlossary getGlossary() {
        return glossary;
    }

    public void setGlossary(ContextGlossary glossary) {
        this.glossary = glossary;
    }

    public ContextMessages getMessages() {
        return messages;
    }

    public void setMessages(ContextMessages messages) {
        this.messages = messages;
    }

    public Feature getAsFeature(Class<?> clazz) {
        ReqItem reqItem = getByClass(clazz);
        if (reqItem == null) {
            return null;
        }
        if (!(reqItem instanceof Feature)) {
            throw new ReqPlayException("The class " + clazz
                    + " is not a Feature. It is a " + reqItem.getType());
        }
        return (Feature) reqItem;
    }

    public Actor getAsActor(Class<?> clazz) {
        ReqItem reqItem = getByClass(clazz);
        if (reqItem == null) {
            return null;
        }
        if (!(reqItem instanceof Actor)) {
            throw new ReqPlayException("The class " + clazz
                    + " is not an Actor. It is a " + reqItem.getType());
        }
        return (Actor) reqItem;
    }

    public Rater getRater() {
        return rater;
    }

    public void setRater(Rater rater) {
        this.rater = rater;
    }

    public int getIdFormatSize() {
        return idFormatSize;
    }

    public void setIdFormatSize(int idFormatSize) {
        this.idFormatSize = idFormatSize;
    }

}
