package com.cidade.inteligente.cidade_inteligente.modulos;

public class ModuloWifi implements ModuloComunicacao{
    @Override
    public void enviarDados(String dados) {
        System.out.println("Enviando dados via Wi-Fi: " + dados);
    }

    @Override
    public String receberComando() {
        return "Comando recebido via Wi-Fi";
    }
}
