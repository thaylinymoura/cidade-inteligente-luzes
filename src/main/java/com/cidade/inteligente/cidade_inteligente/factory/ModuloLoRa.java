package com.cidade.inteligente.cidade_inteligente.factory;

public class ModuloLoRa implements ModuloComunicacao{
    @Override
    public void enviarDados(String dados) {
        System.out.println("Enviando dados via LoRa: " + dados);
    }

    @Override
    public String receberComando() {
        return "Comando recebido via LoRa";
    }
}

