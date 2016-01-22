package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseCase;
import org.reqplay.poc.MSG;
import org.reqplay.poc.ATO.AtorAluno;
import org.reqplay.poc.ATO.AtorCadastrador;
import org.reqplay.poc.IV.IvAluno;
import org.reqplay.poc.NEC.NecGerenciarAlunos;
import org.reqplay.poc.RNG.RngAlunos;
import org.reqplay.poc.model.Aluno;

@ReqItemSpec(id = "CDU01", name = "Alterar Dados do Aluno")
@Doc(artifact = "ECU-Cadastro de Alunos")
@ScenarioSpec(feature = NecGerenciarAlunos.FunAlterarDadosAluno.class)
public class CduAlterarDadosDoAluno extends UseCase{

    @Override
    public void main() {
        as(AtorCadastrador.class).orAs(AtorAluno.class);
        goal("Alterar dados do cadastro de um aluno");
        step(1).include(CduBuscarAluno.class);
        step(2, "O sistema mostra os dados so Aluno selecionado"). view(IvAluno.class);
        step(3, "Usuário preenche os dados que deseja alterar e confirma").input(Aluno.class);
        step(4, "Sistema valida e salva os dados de acordo com a regra ", 
                RngAlunos.DadosDoCadastro.class," e mostra a mensagem ", 
                MSG.ALUNO_ALTERADO_OK);
        
        alternative("A1", "Cancelar Alterações", at(3), "Usuário cancela as alterações");
        step("A1.1", "Sistema limpa a tela com os dados do aluno selecionado");
        step("A1.2", "Reinicia o caso de uso");
        
        exception("E1", "Aluno não encontrado", at(1), "");
        step("E1.1", "Sistema mosra a mensagem ", MSG.ALUNO_NAO_ENCONTRADO);
        step("E1.2").returnTo(1);

        exception("E2", "Dados inválidos", at(4), "");
        step("E2.1", "Sistema identifica os campo inválidos");
        step("E2.2", "Sistema mosra a mensagem ", MSG.ALUNO_SALVAR_NOK);
        
        extension("PE1", "Enviar Email", at(4), "Ator opta por enviar email", CduEnviarEmailCadastro.class );
        
        obs("A Regra de negócio ", RngAlunos.class, " contém mais detalhes sobre este caso de uso");
    }

}
