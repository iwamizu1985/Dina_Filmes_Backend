package com.dinafilmes.backend;

import org.springframework.stereotype.Component;

@Component
public class FilmeEntity {

    private int codigoFilme;
    private String tema;
    private String genero;
    private String diretor;
    private String avaliacao;
    private int anoLancamento;
    private String elenco;
    private int classificacaoIndicativa;
    private String ondeAssistir;
    
    public int getCodigoFilme() {
        return codigoFilme;
    }
    public void setCodigoFilme(int codigoFilme) {
        this.codigoFilme = codigoFilme;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public String getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public String getElenco() {
        return elenco;
    }
    public void setElenco(String elenco) {
        this.elenco = elenco;
    }
    public int getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }
    public void setClassificacaoIndicativa(int classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
    public String getOndeAssistir() {
        return ondeAssistir;
    }
    public void setOndeAssistir(String ondeAssistir) {
        this.ondeAssistir = ondeAssistir;
    }
    
    
    
}
