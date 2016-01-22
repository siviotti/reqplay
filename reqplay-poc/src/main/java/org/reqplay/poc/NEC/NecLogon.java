package org.reqplay.poc.NEC;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.FeatureSpec;
import org.reqplay.annotation.element.NeedSpec;
import org.reqplay.model.Benefit;
import org.reqplay.poc.ATO.AtorAdministrador;
import org.reqplay.poc.ATO.AtorAluno;
import org.reqplay.poc.ATO.AtorCadastrador;
import org.reqplay.poc.ATO.AtorUsuario;

@ReqItemSpec(id = "NEC01", name = "Controlar acesso dos usuários")
@Doc(artifact="DV")
@NeedSpec(benefit = Benefit.CRITICAL)
public class NecLogon {

	@ReqItemSpec(id = "FUN1.1", name = "Efetuar Logon")
	@FeatureSpec(actor = { AtorUsuario.class, AtorCadastrador.class,
			AtorAluno.class, AtorAdministrador.class })
	public class FunLogon {}

	@ReqItemSpec(id = "FUN1.2", name = "Trocar Senha")
	@FeatureSpec(actor = { AtorUsuario.class, AtorCadastrador.class,
			AtorAluno.class, AtorAdministrador.class })
	public class FunTrocaSenha {}

}
