package org.reqplay.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reqplay.ReqPlayException;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.model.code.CodeItem;
import org.reqplay.util.Str;

/**
 * Represents a Requirement Item like Actor, Message, Use Case, Scenario etc.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class ReqItem implements Comparable<ReqItem> {

    // Context (received in the method afterPlay called by the Context)
    private ReqContext context;
    // Spec
    private String id;
    private String name;
    private String type;
    private String className;
    private String description;
    // Defined on Spec
    private int priority;
    private int risk;
    private int complexity; // can be calculated
    private int size; // declared size
    // exclusively calculated
    private int popularity; // simple count of dependents
    private int network; // simple count of dependencies
    private int rating;
    private int calcSize; // calculated size
    // Doc
    private String author;
    private String version;
    private String system;
    private String project;
    private String artifact;
    private ReqItem parent;
    private List<ReqItem> children = new ArrayList<ReqItem>();
    private Map<ReqItem, Integer> dependents = new HashMap<ReqItem, Integer>();
    private Map<ReqItem, Integer> dependencies = new HashMap<ReqItem, Integer>();
    private Map<CodeItem<?>, Integer> dependentCode = new HashMap<CodeItem<?>, Integer>();

    /**
     * @param id
     *            The ID
     * @param name
     *            The name, title, description etc.
     */
    public ReqItem() {
        super();

    }

    /**
     * This method is called after the "Play" process. Each ReqItem makes some
     * instanciations from the classes present in its members (objects).
     */
    public void afterPlay(ReqContext context) {
        this.context = context;
        Collections.sort(children);
    }

    /**
     * The link used in Requirement Documents on format [ID].
     * 
     * @return The Link String like [ID].
     */
    public String shortLink() {
        return "[".concat(id).concat("]");
    }
    /**
     * The link used in Requirement Documents on format [ID] name.
     * 
     * @return The Link String like [ID] name.
     */
    public String longLink() {
        return "[".concat(id).concat("] ").concat(name);
    }
    
    public String link(){
        return shortLink();
    }
    
    public String asText(){
        return idFormatted().concat(" (").concat(type).concat(") ").concat(name);
    }
    
    public String idFormatted(){
        return Str.fill(id, context.getIdFormatSize());
    }
    

    // ******************** Info Refresh ********************

    public void linkTo(ReqItem target) {
        addDependency(target);
        target.addDependent(this);
    }
    
    public void tracedBy(CodeItem<?> codeItem){
        Integer count = dependentCode.get(codeItem);
        if (count == null){
            dependentCode.put(codeItem, 1);
        } else {
            dependentCode.put(codeItem, count+1);
        }
        popularity++;
        codeItem.traceTo(this);
    }

    private void addDependent(ReqItem dependent) {
        Integer count = dependents.get(dependent);
        if (count == null) {
            dependents.put(dependent, 1);
        } else {
            dependents.put(dependent, count + 1);
        }
        popularity++;
    }

    private void addDependency(ReqItem dependency) {
        Integer count = dependencies.get(dependency);
        if (count == null) {
            dependencies.put(dependency, 1);
        } else {
            dependencies.put(dependency, count + 1);
        }
    }

    public void acceptChild(ReqItem child) {
        if (child.getParent() != null) {
            throw new ReqPlayException("Child already has a parent: " + child);
        }
        if (!children.contains(child)) {
            children.add(child);
        }
        child.setParent(this);
        linkTo(child);
    }

    // ******************** Override ********************
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReqItem) {
            ReqItem reqItem = (ReqItem) obj;
            return id.equals(reqItem.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return asText();
    }

    public int compareTo(ReqItem o) {
        return id.compareTo(o.id);
    }

    // ******************** Exceptions ********************
    public ReqPlayException exceptionDuplicatedId() {
        try {
            throw new ReqPlayException("Duplicated id:" + id);
        } catch (ReqPlayException e) {
            e.printStackTrace();
            return e;
        }
    }

    public ReqPlayException exceptionSpecMissing() {
        try {
            throw new ReqPlayException("The class " + getClass()
                    + " must be annoteted with " + ReqItemSpec.class);
        } catch (ReqPlayException e) {
            e.printStackTrace();
            return e;
        }
    }

    // ******************** ETC ********************
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    // ******************** GET / SET ********************

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReqPlayId() {
        return id;
    }

    public String getReqPlayName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Map<ReqItem, Integer> getDependents() {
        return dependents;
    }

    public Map<ReqItem, Integer> getDependencies() {
        return dependencies;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public int getCalcSize() {
        return calcSize;
    }

    public void setCalcSize(int calcSize) {
        this.calcSize = calcSize;
    }

    public List<ReqItem> getChildren() {
        return children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReqItem getParent() {
        return parent;
    }

    public void setParent(ReqItem parent) {
        this.parent = parent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReqContext getContext() {
        return context;
    }

    public void setContext(ReqContext context) {
        this.context = context;
    }

    public Map<CodeItem<?>, Integer> getDependentCode() {
        return dependentCode;
    }
    
    public String traceToStr(){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(dependencies.size());
        sb.append(")");
        for (ReqItem reqItem: dependencies.keySet()){
            sb.append(reqItem.shortLink());
        }
        return sb.toString();
    }
    
    public String tracedByStr (){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(dependents.size() + dependentCode.size());
        sb.append(")");
        for (ReqItem reqItem: dependents.keySet()){
            sb.append(reqItem.shortLink());
        }
        for (CodeItem<?> codeItem: dependentCode.keySet()){
            sb.append(codeItem.getLink());
        }
        return sb.toString();
    }

}
