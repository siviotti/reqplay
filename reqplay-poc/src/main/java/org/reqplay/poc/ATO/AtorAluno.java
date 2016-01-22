package org.reqplay.poc.ATO;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ActorSpec;

@ReqItemSpec(id = "ATO03", name = "Aluno")
@Doc(artifact = "DV")
@ActorSpec(role = "Usuário de um aluno da escola", inputs = "Dados mais atuais do seu próprio cadastro", representative = "Não se aplica")
public class AtorAluno extends AtorUsuario{

	
}
