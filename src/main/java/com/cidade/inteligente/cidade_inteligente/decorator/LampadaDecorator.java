package com.cidade.inteligente.cidade_inteligente.decorator;

import com.cidade.inteligente.cidade_inteligente.factory.Lampada;

public class LampadaDecorator implements Lampada {

    protected Lampada lampadaDecorada; // Referência à lâmpada que está sendo decorada

    public LampadaDecorator(Lampada lampadaDecorada) {
        this.lampadaDecorada = lampadaDecorada;
    }

    // Métodos da interface Lampada, que são simplesmente delegados à lâmpada decorada
    @Override
    public void ligar() {
        this.lampadaDecorada.ligar();
    }

    @Override
    public void desligar() {
        this.lampadaDecorada.desligar();
    }

    @Override
    public double getConsumoEnergia() {
        return this.lampadaDecorada.getConsumoEnergia();
    }

    public Lampada getLampadaDecorada() {
        return lampadaDecorada;
    }

}
