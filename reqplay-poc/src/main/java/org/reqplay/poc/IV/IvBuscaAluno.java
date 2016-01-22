package org.reqplay.poc.IV;

import org.reqplay.annotation.FieldSpec;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.FormSpec;
import org.reqplay.annotation.element.ViewSpec;

@ReqItemSpec(id="IV03", name="Busca de Aluno")
@ViewSpec
public class IvBuscaAluno {

    @ReqItemSpec(id="FORM03", name="Formulário de busca de aluno")
    @FormSpec
    public static class DadosBuscaAluno{
        @FieldSpec(size=50)
        private String matricula;

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        
    }
}
