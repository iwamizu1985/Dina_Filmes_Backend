package com.dinafilmes.backend;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigoUsuario;
    private String nomeUsuario;
    private String email;
    private String telefone;
    private String senha;
    private boolean ativo;
    private boolean aviso1;
    private boolean aviso2;
    @Lob
    private byte[] foto;
    
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public boolean isAviso1() {
        return aviso1;
    }
    public void setAviso1(boolean aviso1) {
        this.aviso1 = aviso1;
    }
    public boolean isAviso2() {
        return aviso2;
    }
    public void setAviso2(boolean aviso2) {
        this.aviso2 = aviso2;
    }
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
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
