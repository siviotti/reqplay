package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseScenario;
import org.reqplay.poc.MSG;
import org.reqplay.poc.ATO.AtorUsuario;
import org.reqplay.poc.NEC.NecLogon;
import org.reqplay.poc.RNF.RnfSeguranca;

@ReqItemSpec(id = "CNU02", name = "Trocar Senha")
@Doc(artifact = "ECN-Logon")
@ScenarioSpec(feature = NecLogon.FunTrocaSenha.class)
public class CnuTrocaSenha extends UseScenario {

    @Override
    public void main() {
        as(AtorUsuario.class);
        iWant("trocar a senha ao efetuar logon");
        after(CnuEfetuarLogon.class);
        step(1.1, "Sistema valida a nova senha de acordo com ",
                RnfSeguranca.Senhas.ReusoDeSenhas.class);
        step(1.2, "Sistema efetua a troca da senha");
        step(1.3, "Sistema mostra a mensagem ", MSG.TROCA_SENHA_OK);
        
        soThat("A senha do usuário foi alterada");
    }

    @Override
    public void however() {
        IF (at(1.1), "A senha não atender a regra ", RnfSeguranca.Senhas.class);
        then("Sistema mostra a mensagem ", MSG.TROCA_SENHA_INVALIDA);
        and("Retorna ao passo 1 do cenário ", CnuEfetuarLogon.class);
        
        IF(at(1.1), "O usuário esciolheu uma das cinco últimas senhas utilizadas");
        then("Sistema mostra a mensagem ", MSG.TROCA_SENHA_JA_USADA);
        and("Retorna ao passo 1 do cenário ", CnuEfetuarLogon.class);
    }

}
