package com.cidade.inteligente.cidade_inteligente.decorator;
import com.cidade.inteligente.cidade_inteligente.factory.Lampada;

public class LampadaComDimmer extends LampadaDecorator{
    private int intensidade;

    public LampadaComDimmer(Lampada lampadaDecorada) {
        super(lampadaDecorada);
        this.intensidade = 100;
    }

    public void setIntensidade(int intensidade) {
        if (intensidade >= 0 && intensidade <= 100) {
            this.intensidade = intensidade;
            System.out.println("Intensidade da lâmpada ajustada para " + intensidade + "%.");
        } else {
            System.out.println("Intensidade inválida. Use um valor entre 0 e 100.");
        }
    }

    public int getIntensidade() {
        return intensidade;
    }

    @Override
    public void ligar() {
        super.ligar();
        System.out.println("Lâmpada ligada com intensidade de " + intensidade + "%.");
    }

    @Override
    public double getConsumoEnergia() {

        return super.getConsumoEnergia() * (intensidade / 100.0);
    }

}
