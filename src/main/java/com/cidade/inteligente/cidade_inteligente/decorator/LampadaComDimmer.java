package com.cidade.inteligente.cidade_inteligente.decorator;
import com.cidade.inteligente.cidade_inteligente.factory.Lampada;

public class LampadaComDimmer extends LampadaDecorator{
    private int intensidade; // 0-100%

    public LampadaComDimmer(Lampada lampadaDecorada) {
        super(lampadaDecorada);
        this.intensidade = 100; // Começa com intensidade total
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
        super.ligar(); // Chama o ligar da lâmpada decorada
        System.out.println("Lâmpada ligada com intensidade de " + intensidade + "%.");
    }

    @Override
    public double getConsumoEnergia() {
        // O consumo de energia pode variar com a intensidade
        // Exemplo: consumo proporcional à intensidade
        return super.getConsumoEnergia() * (intensidade / 100.0);
    }

}
