package com.dinafilmes.backend;

import java.time.LocalDateTime;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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
    private String foto;
    private boolean ativo;
    private boolean aviso1;
    private boolean aviso2;
     

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @Lob
    private Byte[] fotoUsuario;

    private String fotoUsuarioMimeType;


    public String getFotoUsuarioMimeType() {
        return fotoUsuarioMimeType;
    }
    public void setFotoUsuarioMimeType(String fotoUsuarioMimeType) {
        this.fotoUsuarioMimeType = fotoUsuarioMimeType;
    }
    public Byte[] getFotoUsuario() {
        return fotoUsuario;
    }
    public void setFotoUsuario(Byte[] fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
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

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    
}
