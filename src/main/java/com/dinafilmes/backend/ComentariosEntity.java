package com.dinafilmes.backend;

import org.springframework.stereotype.Component;

@Component
public class ComentariosEntity {

    private String comentario;
    private int codigoComentario;
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public int getCodigoComentario() {
        return codigoComentario;
    }
    public void setCodigoComentario(int codigoComentario) {
        this.codigoComentario = codigoComentario;
    }

    
    
}
