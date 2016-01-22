package org.reqplay.poc.model;

import org.reqplay.annotation.FieldSpec;
import org.reqplay.annotation.ReqItemSpec;
import org.reqplay.annotation.element.FormSpec;

@ReqItemSpec(id="FORM01", name="Entidade Usuario")
@FormSpec
public class Usuario {
    
    @FieldSpec(size=20, required=true)
    private String login;
    @FieldSpec(size=20, required=true)
    private String senha;
        
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
