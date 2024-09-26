package com.dinafilmes.backend;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.IdClass;


@Entity
@Table(name = "lista", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigoFilme", "codigoUsuario"})
})
public class ListaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoLista;
    private int codigoFilme;
    private int codigoUsuario;
    private boolean filmeFavorito;
    private boolean filmeJaVisto;
    private boolean filmeAAssitir;

    public int getCodigoLista() {
        return codigoLista;
    }
    public void setCodigoLista(int codigoLista) {
        this.codigoLista = codigoLista;
    }
    public int getCodigoFilme() {
        return codigoFilme;
    }
    public void setCodigoFilme(int codigoFilme) {
        this.codigoFilme = codigoFilme;
    }
    public int getCodigoUsuario() {
        return codigoUsuario;
    }
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    public boolean isFilmeFavorito() {
        return filmeFavorito;
    }
    public void setFilmeFavorito(boolean filmeFavorito) {
        this.filmeFavorito = filmeFavorito;
    }
    public boolean isFilmeJaVisto() {
        return filmeJaVisto;
    }
    public void setFilmeJaVisto(boolean filmeJaVisto) {
        this.filmeJaVisto = filmeJaVisto;
    }
    public boolean isFilmeAAssitir() {
        return filmeAAssitir;
    }
    public void setFilmeAAssitir(boolean filmeAAssitir) {
        this.filmeAAssitir = filmeAAssitir;
    }

       
}
