package org.reqplay.poc.ERF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.element.scenario.UseCase;
import org.reqplay.poc.MSG;
import org.reqplay.poc.ATO.AtorCadastrador;
import org.reqplay.poc.IV.IvAluno;
import org.reqplay.poc.NEC.NecGerenciarAlunos;
import org.reqplay.poc.RNG.RngAlunos;
import org.reqplay.poc.model.Aluno;

@ReqItemSpec(id = "CDU03", name = "Incluir Aluno")
@Doc(artifact = "ECU-Cadastro de Alunos")
@ScenarioSpec(feature = NecGerenciarAlunos.FunIncluirAluno.class)
public class CduIncluirAluno extends UseCase{

    @Override
    public void main() {
        as(AtorCadastrador.class);
        
        goal("Cadastrar um aluno");
        step(1, "Usuário seleciona a opção Incluir Aluno.");
        step(2, "Sistema mostra a página de cadastro de aluno.").view(IvAluno.class);
        step(3, "Usuário informa os dados do aluno e confirma.").input(Aluno.class);
        step(4.1, "Sistema valida os dados de acordo com a regra ",
                RngAlunos.DadosDoCadastro.class," e salva os dados do aluno");
        step(4.2, "Sistema mostra a mensagem ", MSG.ALUNO_INCLUIDO_OK);
        postCondition("O Sistema gera um arquivo TXT com a ficha do aluno para exportação");
        
        exception("E1", "Dados inválidos", at(4.1));
        step("E1.1", "Sistema encontra campos inválidos na ficha do Aluno");
        step("E1.2", "Sistema mostra a mensagem" + MSG.ALUNO_SALVAR_NOK);

        exception("E2", "Erro ao gravar TXT", at(4.1));
        step("E2.1", "Sistema não consegue gravar arquivo TXT");
        step("E2.2", "Sistema mostra a mensagem" + MSG.ALUNO_SALVAR_NOK);
        
        extension("PE1", "Enviar Email", at(4.1), "Ator opta por enviar email", CduEnviarEmailCadastro.class);
        
        obs("A Regra de negócio ", RngAlunos.class, " contém mais detalhes sobre esta caso de uso");
        
    }
    
    
}
