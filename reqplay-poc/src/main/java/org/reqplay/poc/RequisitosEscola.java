package org.reqplay.poc;

import org.reqplay.ReqPlay;
import org.reqplay.annotation.ReqContextSpec;
import org.reqplay.annotation.ReqSyntax;
import org.reqplay.model.ReqContext;

//@ReqContextSpec(name = "EscolaContext", system = RequisitosEscola.SYSTEM, idFormatSize=8)
@ReqSyntax(system="Sistema", user="Usuário")
public class RequisitosEscola extends ReqContext {
    
    public static final String SYSTEM = "Escola";

    public static void main(String[] args) {
        ReqPlay.context();
    }
}
