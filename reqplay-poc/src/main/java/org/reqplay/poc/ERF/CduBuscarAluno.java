package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseCase;
import org.reqplay.poc.IV.IvBuscaAluno;
import org.reqplay.poc.NEC.NecGerenciarAlunos;
import org.reqplay.poc.model.Aluno;

@ReqItemSpec(id = "CDU02", name = "Buscar Aluno")
@Doc(artifact = "ECU-Cadastro de Alunos")
@ScenarioSpec(feature = NecGerenciarAlunos.FunAlterarDadosAluno.class)
public class CduBuscarAluno extends UseCase{

    @Override
    public void main() {
        step(1, "Sistema mostra a página de busca de alunos").view(IvBuscaAluno.class);
        step(2, "Usuário informa a matrícula desejada").input(IvBuscaAluno.DadosBuscaAluno.class);
        step(3, "Sistema busca a ficha do aluno com base na matrícula").inquire(Aluno.class);
    }

}
