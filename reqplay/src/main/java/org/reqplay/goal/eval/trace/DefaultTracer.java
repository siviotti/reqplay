package org.reqplay.goal.eval.trace;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.reqplay.ReqPlayException;
import org.reqplay.annotation.ReqTrace;
import org.reqplay.model.ContainerItem;
import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.code.ClassItem;
import org.reqplay.model.code.CodeItem;
import org.reqplay.model.code.FieldItem;
import org.reqplay.model.code.MethodItem;
import org.reqplay.util.ResourceList;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/07/2013
 * 
 */
public class DefaultTracer implements Tracer {

    private ResourceList resourceList = new ResourceList();

    public void trace(ReqContext context) {
        // Traceability between ReqItens
        List<ContainerItem> containers = context.getContainerItems();
        for (ContainerItem container : containers) {
            // System.out.println("Container:"+container.getId());
            for (ReqItem reference : container.getReferences()) {
                // System.out.println("reference:"+reference.getId());
                container.linkTo(reference);
            }
        }
        // Traceability from Code to ReqItem
        List<Class<?>> classes = resourceList.listTypes(true, true, true, true);
        traceCode(context, classes);
    }

    private void traceCode(ReqContext context, List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            traceClass(context, clazz);
        }

    }

    private void traceClass(ReqContext context, Class<?> clazz) {
        if (clazz.getCanonicalName().equals("org.reqplay.escola.domain.Aluno")) {
            System.out.println("Code:" + clazz.getCanonicalName());
        }
        // Class
        CodeItem<?> parent = createCodeItem(clazz, null);
        if (clazz.isAnnotationPresent(ReqTrace.class)) {
            traceCodeItems(context, parent, null,
                    clazz.getAnnotation(ReqTrace.class));
        }
        // Methods
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ReqTrace.class)) {
                traceCodeItems(context, method, parent,
                        method.getAnnotation(ReqTrace.class));
            }
        }
        // Fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ReqTrace.class)) {
                traceCodeItems(context, field, parent,
                        field.getAnnotation(ReqTrace.class));
            }
        }
        // Constructor TODO
        // Package TODO
    }

    private void traceCodeItems(ReqContext context, CodeItem<?> codeItem,
            CodeItem<?> parent, ReqTrace reqTrace) {
        // List of ReqItemClass (ReqItem subclasses)
        Class<? extends ReqItem>[] reqItemsClasses = reqTrace.reqItem();
        for (Class<? extends ReqItem> reqItemClass : reqItemsClasses) {
            traceCodeItem(context, reqItemClass, codeItem);
        }
        // List of Any Class (Annotated classes)
        Class<?>[] specItemsClasses = reqTrace.specItem();
        for (Class<?> specItemClass : specItemsClasses) {
            traceCodeItem(context, specItemClass, codeItem);
        }
    }

    private void traceCodeItems(ReqContext context, Object codeElement,
            CodeItem<?> parent, ReqTrace reqTrace) {
        CodeItem<?> codeItem = createCodeItem(codeElement, parent);
        traceCodeItems(context, codeItem, parent, reqTrace);
    }

    private void traceCodeItem(ReqContext context, Class<?> itemClass,
            CodeItem<?> codeItem) {
        ReqItem reqItem = context.getByClass(itemClass);
        if (reqItem == null) {
            throw new ReqPlayException("ReqItem class not found:"
                    + itemClass.getCanonicalName());
        }
        reqItem.tracedBy(codeItem); // <-- TRACE HERE
    }

    private CodeItem<?> createCodeItem(Object codeElement, CodeItem<?> parent) {
        if (codeElement instanceof Class) {
            return new ClassItem((Class<?>) codeElement, parent);
        } else if (codeElement instanceof Method) {
            return new MethodItem((Method) codeElement, parent);
        } else if (codeElement instanceof Field) {
            return new FieldItem((Field) codeElement, parent);
        }
        throw new ReqPlayException("Invalid CodeElement:"
                + codeElement.getClass());
    }

}
