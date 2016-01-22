package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseScenario;
import org.reqplay.poc.LEL;
import org.reqplay.poc.MSG;
import org.reqplay.poc.ATO.AtorUsuario;
import org.reqplay.poc.IV.IvAluno;
import org.reqplay.poc.IV.IvLogon;
import org.reqplay.poc.NEC.NecLogon;
import org.reqplay.poc.RNG.RngLogon;
import org.reqplay.poc.model.Usuario;

@ReqItemSpec(id = "CNU01", name = "Efetuar Logon")
@Doc(artifact = "ECN-Logon")
@ScenarioSpec(feature = NecLogon.FunLogon.class)
public class CnuEfetuarLogon extends UseScenario {

    @Override
    public void main() {
        as(AtorUsuario.class);
        iWant("Efetuar logon e entrar no sistema");
        step(1, "Sistema inicia");
        step(2, "Sistema mostra a tela de login.").view(IvLogon.class);
        step(3, "Usuario preenche o logon e senha e confirma.").input(
                Usuario.class);
        step(4.1, "Sistema valida dados (login/senha).");
        step(4.2, "Sistema mostra o cadastro de Alunos.").view(IvAluno.class);
        step(4.3, "É gerado o log ", LEL.Logon.class);
        step(4.4, "Sistema mostra a mensagem ", MSG.LOGON_OK);
        soThat("O usuário está logado no sistema");
    }

    @Override
    public void however() {
        IF(at(4.1), "O login ou senha estiverem errados");
        then("Sistema mostra a mensagem de login inválido ", MSG.LOGON_NOK);
        and("Se for o terceiro erro, bloqueia o login de acordo com ",
                RngLogon.BloqueioDeLogin.class);
        and().returnTo(2);
    }

}
