package com.cidade.inteligente.cidade_inteligente.factory;

public class LampadaLED implements  Lampada{
    @Override
    public void ligar() {
        System.out.println("Lâmpada LED ligada.");
    }

    @Override
    public void desligar() {
        System.out.println("Lâmpada LED desligada.");
    }

    @Override
    public double getConsumoEnergia() {
        return 15.0; // 15W
    }
}

