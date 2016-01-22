package org.reqplay.poc.NEC;

import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.FeatureSpec;
import org.reqplay.annotation.element.NeedSpec;
import org.reqplay.model.Benefit;
import org.reqplay.poc.ATO.AtorAdministrador;
import org.reqplay.poc.ATO.AtorAluno;
import org.reqplay.poc.ATO.AtorCadastrador;

@ReqItemSpec(id = "NEC02", name = "Gerenciar Cadastro de Alunos")
@NeedSpec(benefit = Benefit.CRITICAL)
public class NecGerenciarAlunos {
	
	@ReqItemSpec(id = "FUN2.1", name = "Buscar Aluno")
	@FeatureSpec()
	public class FunBuscarAluno {

	}
	
	@ReqItemSpec(id = "FUN2.2", name = "Incluir Aluno")
	@FeatureSpec(actor={AtorCadastrador.class})
	public class FunIncluirAluno {

	}

	@ReqItemSpec(id = "FUN2.3", name = "Alterar Dados do Aluno")
	@FeatureSpec(actor={AtorAdministrador.class, AtorAluno.class})
	public class FunAlterarDadosAluno {

	}

	@ReqItemSpec(id = "FUN2.4", name = "Excluir Aluno")
	@FeatureSpec(actor={AtorAdministrador.class})
	public class FunExcluirDadosAluno {

	}

}
