package com.dinafilmes.backend;

import org.springframework.stereotype.Component;

@Component
public class ListaEntity {

    private int codigoFilme;
    private int codigoUsuario;
    private boolean filmeFavorito;
    private boolean filmeJaVisto;
    private boolean filmeAAssitir;
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
