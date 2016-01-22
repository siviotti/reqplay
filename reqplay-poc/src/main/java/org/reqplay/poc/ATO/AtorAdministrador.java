package org.reqplay.poc.ATO;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ActorSpec;

@ReqItemSpec(id = "ATO04", name = "Administrador")
@Doc(artifact = "DV")
@ActorSpec(role = "Usuário administrador", 
    inputs = "Ação de exclusão de um cadastro de aluno", 
    representative = "Beltrano")
public class AtorAdministrador extends AtorCadastrador {
}
