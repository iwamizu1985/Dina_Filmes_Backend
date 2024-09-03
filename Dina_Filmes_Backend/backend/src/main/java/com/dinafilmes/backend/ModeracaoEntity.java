package com.dinafilmes.backend;

import org.springframework.stereotype.Component;

@Component
public class ModeracaoEntity {

    private int codigoModeracao;
    private String tipoDenuncia;
    public int getCodigoModeracao() {
        return codigoModeracao;
    }
    public void setCodigoModeracao(int codigoModeracao) {
        this.codigoModeracao = codigoModeracao;
    }
    public String getTipoDenuncia() {
        return tipoDenuncia;
    }
    public void setTipoDenuncia(String tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    
    
}
