package org.reqplay.poc.ATO;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.ActorSpec;
import org.reqplay.poc.GLO;

@ReqItemSpec(id = "ATO01", name = "Usuário")
@Doc(artifact = "DV")
@ActorSpec(role = "Usuário genérico do sistema", inputs = "Dados de login e senha para efetuar logon" + GLO.Aluno, representative = "Não se aplica")
public class AtorUsuario {

}
