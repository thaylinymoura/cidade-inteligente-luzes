package com.cidade.inteligente.cidade_inteligente.config;

public class ConfigLimiar {
    private String tipoConfiguracao;
    private String valor;

    public ConfigLimiar(String tipoConfiguracao, String valor) {
        this.tipoConfiguracao = tipoConfiguracao;
        this.valor = valor;
    }

    // Getters
    public String getTipoConfiguracao() {
        return tipoConfiguracao;
    }

    public String getValor() {
        return valor;
    }
}