package org.reqplay.poc;

import org.reqplay.annotation.Doc;
import org.reqplay.annotation.element.TermSpec;
import org.reqplay.annotation.group.Glossary;

@Glossary
@Doc(artifact="GLO")
public class GLO {

	@TermSpec(id="T001", entity=org.reqplay.poc.model.Aluno.class)
	public static final String Aluno = "Estudante da escola.";
	@TermSpec(id="T002", url="http://pt.wikipedia.org/wiki/Escola")
	public static final String Escola = "Local onde um Aluno estuda.";
	
}
