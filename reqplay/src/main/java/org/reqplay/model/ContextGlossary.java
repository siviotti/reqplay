package org.reqplay.model;

import java.util.HashMap;
import java.util.Map;

import org.reqplay.ReqPlayException;
import org.reqplay.model.element.Term;

/**
 * The term present on the Context. The key attribute is the 'name'. The term
 * name is the field name declared on the code.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/07/2013
 * 
 */
public class ContextGlossary {

    private Map<String, Term> terms = new HashMap<String, Term>();

    public ContextGlossary put(Term term) {
        if (terms.containsKey(term.getName())) {
            throw new ReqPlayException("Duplicated term in Glossary:"
                    + term.getName());
        }
        terms.put(term.getName(), term);
        return this;
    }

    public Term get(String name) {
        return terms.get(name);
    }

    public Map<String, Term> getTerms() {
        return terms;
    }
    
    public boolean contains(String name){
        return terms.containsKey(name);
                
    }

}
