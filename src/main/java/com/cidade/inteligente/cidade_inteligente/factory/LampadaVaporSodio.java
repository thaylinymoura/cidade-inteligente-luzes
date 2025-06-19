package com.cidade.inteligente.cidade_inteligente.factory;

public class LampadaVaporSodio implements Lampada{
    @Override
    public void ligar() {
        System.out.println("Lâmpada de Vapor de Sódio ligada."); // Adicionado para ver no console
    }

    @Override
    public void desligar() {
        System.out.println("Lâmpada de Vapor de Sódio desligada."); // Adicionado para ver no console
    }

    @Override
    public double getConsumoEnergia() {
        return 100.0; // Exemplo: 100W
    }
}

