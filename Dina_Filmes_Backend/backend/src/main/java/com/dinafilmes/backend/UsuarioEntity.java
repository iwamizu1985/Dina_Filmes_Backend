package com.dinafilmes.backend;

import org.springframework.stereotype.Component;

@Component
public class UsuarioEntity {
    private int codigoUsuario;
    private String nomeUsuario;
    private String email;
    private String telefone;
    private boolean foto;
    private String senha;
    
    public int getCodigoUsuario() {
        return codigoUsuario;
    }
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public boolean isFoto() {
        return foto;
    }
    public void setFoto(boolean foto) {
        this.foto = foto;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
