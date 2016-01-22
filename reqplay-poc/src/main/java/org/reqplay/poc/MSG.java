package org.reqplay.poc;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.element.MessageSpec;
import org.reqplay.annotation.group.Messages;
import org.reqplay.model.element.Message;

@Messages
@Doc(artifact="EMS")
public class MSG {

	// Info
	@MessageSpec(id = "MIN01")
	public static final String LOGON_OK = "Logon efetuado com sucesso";
	@MessageSpec(id = "MIN02")
	public static final String TROCA_SENHA_OK = "Troca de senha efetuada.";
	@MessageSpec(id = "MIN11")
	public static final String ALUNO_INCLUIDO_OK = "Aluno incluído com sucesso.";
	@MessageSpec(id = "MIN12")
	public static final String ALUNO_ALTERADO_OK = "Dados de aluno alterados com sucesso";
	@MessageSpec(id = "MIN13")
	public static final String ALUNO_EXCUIDO_OK = "Aluno excluído";
    @MessageSpec(id = "MIN99")
    public static final String PARABENS_ANIVERSARIO = "Parabéns pelo seu aniversário";

	// Erro
	@MessageSpec(id = "MER01",severity = Message.ERROR)
	public static final String LOGON_NOK = "Usuário ou senha inválido";
	@MessageSpec(id = "MER02", severity = Message.ERROR)
	public static final String TROCA_SENHA_INVALIDA = "Senha fora do padrão";
    @MessageSpec(id = "MER03", severity = Message.ERROR)
    public static final String TROCA_SENHA_JA_USADA = "Senha já foi utilizada";
	@MessageSpec(id = "MER11", severity = Message.ERROR)
	public static final String ALUNO_SALVAR_NOK = "Erro ao salvar os dados";
    @MessageSpec(id = "MER12", severity = Message.ERROR)
    public static final String ALUNO_NAO_ENCONTRADO = "A matricula digitada não corresponde a nenhum aluno";

}
