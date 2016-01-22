package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseCase;
import org.reqplay.poc.MSG;
import org.reqplay.poc.ATO.AtorAdministrador;
import org.reqplay.poc.ATO.AtorCadastrador;
import org.reqplay.poc.IV.IvAluno;
import org.reqplay.poc.NEC.NecGerenciarAlunos;
import org.reqplay.poc.model.Aluno;

@ReqItemSpec(id = "CDU04", name = "Excluir Aluno")
@Doc(artifact = "ECU-Cadastro de Alunos")
@ScenarioSpec(feature = NecGerenciarAlunos.FunExcluirDadosAluno.class)
public class CduExcluirAluno extends UseCase{

    @Override
    public void main() {
        as(AtorAdministrador.class);
        goal("Excluir um Aluno do Cadastro");
        step(1).include(CduBuscarAluno.class);
        step(2, "O sistema mostra os dados so Aluno selecionado").view(IvAluno.class);
        step(3, "Usuário seleciona a opção excluir");
        step(4, "Sistema mostra a mensagem de confirmaçao");
        step(5, "Usuário confirma a exclusão");
        step(6, "Sistema exclui o aluno do cadastro e mostra a mensagem", MSG.ALUNO_EXCUIDO_OK);
        
        exception("E1", "Aluno não encontrado", at(1), "");
        step("E1.1", "Sistema mosra a mensagem ", MSG.ALUNO_NAO_ENCONTRADO);
        step("E1.2").returnTo(1);

    }

}
