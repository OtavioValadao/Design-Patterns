package com.patterns.stocktrading.singleton;

public class ConfiguracaoSistema {

    private static ConfiguracaoSistema instanciaUnica;

    private String fonteDadosPadrao;
    private String moedaPadrao;
    private boolean modoSimulacao;

    public ConfiguracaoSistema() {
        this.fonteDadosPadrao = "B3";
        this.moedaPadrao = "BRL";
        this.modoSimulacao = true;
    }

    // 🔒 synchronized = apenas UMA thread por vez pode entrar aqui
    public static synchronized ConfiguracaoSistema getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new ConfiguracaoSistema();
        }
        return instanciaUnica;
    }

    public String getFonteDadosPadrao() {
        return fonteDadosPadrao;
    }

    public void setFonteDadosPadrao(String fonteDadosPadrao) {
        this.fonteDadosPadrao = fonteDadosPadrao;
    }

    public String getMoedaPadrao() {
        return moedaPadrao;
    }

    public void setMoedaPadrao(String moedaPadrao) {
        this.moedaPadrao = moedaPadrao;
    }

    public boolean isModoSimulacao() {
        return modoSimulacao;
    }

    public void setModoSimulacao(boolean modoSimulacao) {
        this.modoSimulacao = modoSimulacao;
    }
}
