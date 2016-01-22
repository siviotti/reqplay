package org.reqplay.poc.model;

import java.util.Date;

import org.reqplay.annotation.FieldSpec;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.FormSpec;

@ReqItemSpec(id="FORM02", name="Entidade Aluno")
@FormSpec
public class Aluno {

    @FieldSpec(size=10, required=true)
    private String matricula;
    @FieldSpec(size=50, required=true)
    private String nome;
    @FieldSpec(required=true, format="dd/MM/yyyy")
    private Date nascimento;
    @FieldSpec(required=true)
    private boolean ativo = true;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
