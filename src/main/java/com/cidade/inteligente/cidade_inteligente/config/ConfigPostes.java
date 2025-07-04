package com.cidade.inteligente.cidade_inteligente.config;

public class ConfigPostes {
    private String idPoste;
    private String tipo;

    public ConfigPostes(String idPoste, String tipo) {
        this.idPoste = idPoste;
        this.tipo = tipo;
    }

    // Getters
    public String getId() {
        return idPoste;
    }

    public String getTipo() {
        return tipo;
    }
}