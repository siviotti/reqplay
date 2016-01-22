package org.reqplay.model.fp.data;

import org.reqplay.model.fp.Complexity;
import org.reqplay.model.fp.FunctionPoint;

import static org.reqplay.model.fp.Complexity.*;

/**
 * Superclas for Data Function Point.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 04/07/2013
 * 
 */
public class DataFP extends FunctionPoint {

    private static Complexity[][] TABLE = {
            // _______1-19|20-50|>50
            /* _1_ */{ LOW, LOW, AVG },
            /* 2-5 */{ LOW, LOW, AVG },
            /* >5_ */{ LOW, LOW, AVG } };

    private int dataElementTypes;
    private int recordElementTypes;

    @Override
    public Complexity getComplexity() {
        int line = 1;
        int col = 1;
        if (dataElementTypes < 20){
            col = 1;
        } else if (dataElementTypes < 50){
            col = 2;
        } else {
            col = 3;
        }
        if (recordElementTypes == 1){
            line = 1;
        } else if (recordElementTypes < 5){
            line = 2;
        } else {
            line = 3;
        }
        return TABLE[line][col];
    }

    public int getDataElementTypes() {
        return dataElementTypes;
    }

    public void setDataElementTypes(int dataElementTypes) {
        this.dataElementTypes = dataElementTypes;
    }

    public int getRecordElementTypes() {
        return recordElementTypes;
    }

    public void setRecordElementTypes(int recordElementTypes) {
        this.recordElementTypes = recordElementTypes;
    }

}
