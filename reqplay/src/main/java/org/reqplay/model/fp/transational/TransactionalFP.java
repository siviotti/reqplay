package org.reqplay.model.fp.transational;

import org.reqplay.model.fp.Complexity;
import org.reqplay.model.fp.FunctionPoint;

/**
 * Superclass for Transactional Function Points.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 04/07/2013
 * 
 */
public class TransactionalFP extends FunctionPoint {

    private int dataElementTypes;
    private int fileTypesReferenced;

    @Override
    public Complexity getComplexity() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getFileTypesReferenced() {
        return fileTypesReferenced;
    }

    public void setFileTypesReferenced(int fileTypesReferenced) {
        this.fileTypesReferenced = fileTypesReferenced;
    }

    public int getDataElementTypes() {
        return dataElementTypes;
    }

    public void setDataElementTypes(int dataElementTypes) {
        this.dataElementTypes = dataElementTypes;
    }

}
