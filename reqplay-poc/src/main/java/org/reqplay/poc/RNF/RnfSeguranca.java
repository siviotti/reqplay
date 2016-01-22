package org.reqplay.poc.RNF;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.NonFunctionalSpec;

@ReqItemSpec(id = "RNF1", name = "Segurança")
@Doc(artifact="RNF")
@NonFunctionalSpec("Segurança")
public class RnfSeguranca {

	@ReqItemSpec(id = "RNF1.1", name = "Senhas Fortes")
	@NonFunctionalSpec("As senhas devem ser fortes co números e letras.")
	public class Senhas {
		@ReqItemSpec(id = "RNF1.1.1", name = "Reúso de Senhas")
		@NonFunctionalSpec("Na alteração de senha não deve ser possível usar uma das 5 últimas.")
		public class ReusoDeSenhas {}
	}

	@ReqItemSpec(id = "RNF1.2", name = "Cripitografia")
	@NonFunctionalSpec("As senhas devem se armazenadas criptografadas de forma assimétrica.")
	public class Criptografia {}
}
