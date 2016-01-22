package org.reqplay.poc.ATO;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ActorSpec;

@ReqItemSpec(id = "ATO02", name = "Cadastrador")
@Doc(artifact = "DV")
@ActorSpec(role = "Usuário cadastrador", inputs = "Dados de alunos no cadastro inicial", representative = "Fulano")
public class AtorCadastrador extends AtorUsuario{

}
