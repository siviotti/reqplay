package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseCase;
import org.reqplay.poc.IV.IvAluno;
import org.reqplay.poc.NEC.NecGerenciarAlunos;
import org.reqplay.poc.RNG.RngAlunos;
import org.reqplay.poc.model.Aluno;

@ReqItemSpec(id = "CDU05", name = "Enviar Email de Cadastro")
@Doc(artifact = "ECU-Cadastro de Alunos")
@ScenarioSpec(feature = NecGerenciarAlunos.FunIncluirAluno.class)
public class CduEnviarEmailCadastro extends UseCase{

    @Override
    public void main() {
        goal("Enviar email de cadastro");
        step(1, "Sistema envia um email de cadastro para o Aluno");
        postCondition("É gerado um log de envio do email");
    }

}
