package org.reqplay.poc.RNG;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.RuleSpec;
import org.reqplay.model.element.Rule;
import org.reqplay.poc.ATO.AtorCadastrador;
import org.reqplay.poc.IV.IvAluno;

@ReqItemSpec(id = "RNG1", name = "Regras sobre Cadastro de Alunos")
@Doc(artifact = "RNG")
@RuleSpec
public class RngAlunos extends Rule {

    // Regra de entrada de dados - define o formulário de entrada
    @ReqItemSpec(id = "RNG1.1", name = "Dados do Cadastro")
    @RuleSpec
    public static class DadosDoCadastro extends Rule {
        public void spec() {
            _("Na operação de consulta os dados são exibidos somente para leitura.");
            _(" Os campos da página ", IvAluno.class,
                    " deve estar desabilitados (cor cinza).");
        }
    }

    // Regra curta - O texto é definido direto na anotação
    @ReqItemSpec(id = "RNG1.2", name = "Restrição sobre alunos inativos")
    @RuleSpec("Os alunos inativos aparecem somente para o Administrador")
    public static class FiltrarAlunosInativos extends Rule {
    }

    // Regra verbosa com concatenação e referências a outros elementos
    /**
     * Regra de formação da matrícula - A matrícula do aluno deve seguir a
     * seguinte regra de formação:<br>
     * - Deve ser iniciado com o ano de ingresso do aluno (4 dígitos).<br>
     * - O próximo caractere deve ser 1 para primeiro semestre e 2 para o
     * segundo.<br>
     * - A teceira parte é composta pelo número sequencial com cinco dígitos.<br>
     * <P>
     * A matrícula do aluno deve ser única para todas as unidades da escola.O
     * sistema deve fazer o controle das unidades através de um controle de
     * dados centralizado.Todas as unidades devem seguir a regra [RNG1.1] para
     * os dadoscoletados pelo [ATO02] Cadastrador.
     * 
     * @see org.reqplay.poc.ATO.AtorCadastrador [ATO02]
     * @see org.reqplay.poc.RNG.RngAlunos.DadosDoCadastro [RNG1.1]
     */
    @ReqItemSpec(id = "RNG1.3", name = "Regra de formação da matrícula")
    @RuleSpec
    public static class FormacaoMatricula extends Rule {
        public void spec() {
            _("A matrícula do aluno deve seguir a seguinte regra de formação:");
            $("- Deve ser iniciado com o ano de ingresso do aluno (4 dígitos)");
            $("- O próximo caractere deve ser 1 para primeiro semestre e 2 para o segundo");
            $("- A teceira parte é composta pelo número sequencial com cinco dígitos");
            $("A matrícula do aluno deve ser única para todas as unidades da escola.");
            _("O sistema deve fazer o controle das unidades através de controle central.");
            _("Todas as unidades devem seguir a regra ", DadosDoCadastro.class,
                    " para os dados");
            _("coletados pelo ", AtorCadastrador.class);
        };
    }

}
