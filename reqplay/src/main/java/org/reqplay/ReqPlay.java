package org.reqplay;

import java.util.List;

import org.reqplay.annotation.ReqContextSpec;
import org.reqplay.model.ReqContext;
import org.reqplay.util.ResourceList;

/**
 * Main Façade. This classe store the ReqItems instances.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public final class ReqPlay {

    private static final String TO_MANY_REQ_CONTEXTS = "To many ReqContexts";
    private static final String MUST_EXTENDS_REQ_CONTEXT = "Context must extends ReqContext";

    private static ReqContext context;
    private static Class<? extends ReqContext> contextClass;

    static {
        ResourceList resourceList = new ResourceList();
        List<Class<?>> classes = resourceList.listClasses();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(ReqContextSpec.class)) {
                if (contextClass != null) {
                    throw new ReqPlayException(TO_MANY_REQ_CONTEXTS);
                }
                try {
                    clazz.asSubclass(ReqContext.class);
                } catch (ClassCastException cce) {
                    throw new ReqPlayException(MUST_EXTENDS_REQ_CONTEXT);
                }
                contextClass = (Class<? extends ReqContext>) clazz;
            }
        }
    }
    
    private static void createContext(){
        try {
            context = contextClass.newInstance();
            context.load();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }        
    }

    /**
     * Returns the <code>ReqContext</code> instance loaded from the classpath.
     * 
     * @return Tha instance of <code>ReqContext</code>.
     */
    public static ReqContext context() {
        if (context == null) {
            createContext();
        }
        return context;
    }

}
