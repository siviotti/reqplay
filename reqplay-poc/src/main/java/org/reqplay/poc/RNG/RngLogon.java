package org.reqplay.poc.RNG;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.RuleSpec;
import org.reqplay.model.element.Rule;

@ReqItemSpec(id = "RNG2", name = "Regras sobre Logon")
@Doc(artifact="RNG")
@RuleSpec
public class RngLogon extends Rule{

    @ReqItemSpec(id = "RNG2.1", name = "Dados do Cadastro")
    @RuleSpec("Se o usuário errar três vezes a senha terá seu login bloqueado")
    public static class BloqueioDeLogin extends Rule {
    }
    
   
}
