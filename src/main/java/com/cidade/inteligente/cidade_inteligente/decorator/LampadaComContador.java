package com.cidade.inteligente.cidade_inteligente.decorator;

import com.cidade.inteligente.cidade_inteligente.factory.Lampada;

public class LampadaComContador extends LampadaDecorator{

    private int ciclosLigadoDesligado;

    public LampadaComContador(Lampada lampadaDecorada) {
        super(lampadaDecorada);
        this.ciclosLigadoDesligado = 0;
    }

    @Override
    public void ligar() {
        super.ligar();
        ciclosLigadoDesligado++;
        System.out.println("Lâmpada ligada. Ciclos: " + ciclosLigadoDesligado);
    }

    @Override
    public void desligar() {
        super.desligar();
        System.out.println("Lâmpada desligada.");
    }

    public int getCiclosLigadoDesligado() {
        return ciclosLigadoDesligado;
    }
}
