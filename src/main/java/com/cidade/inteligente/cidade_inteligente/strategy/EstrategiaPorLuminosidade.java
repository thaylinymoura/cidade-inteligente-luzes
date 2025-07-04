package com.cidade.inteligente.cidade_inteligente.strategy;

import com.cidade.inteligente.cidade_inteligente.factory.Sensor;
import com.cidade.inteligente.cidade_inteligente.factory.SensorLuminosidade;
import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

public class EstrategiaPorLuminosidade implements ComportamentoLigamento {
    private double limiarLuminosidade; // Abaixo deste valor (Lux), a luz liga

    public EstrategiaPorLuminosidade(double limiarLuminosidade) {
        this.limiarLuminosidade = limiarLuminosidade;
    }

    @Override
    public void avaliar(PosteDeLuz poste) {
        Sensor sensor = poste.getSensor();

        // Verifica se o poste possui um sensor e se é do tipo certo
        if (sensor != null && sensor instanceof SensorLuminosidade) {
            double luminosidadeAtual = sensor.medirLuminosidade();
            System.out.println("Poste " + poste.getId() + " - Luminosidade atual: " + luminosidadeAtual + " Lux."); // Para
                                                                                                                    // depuração

            if (luminosidadeAtual < limiarLuminosidade) {
                if (!poste.isLigado()) {
                    poste.ligar();
                }
            } else {
                if (poste.isLigado()) {
                    poste.desligar();
                }
            }
        } else {
            System.out.println(
                    "Poste " + poste.getId() + " não possui um sensor de luminosidade válido para esta estratégia.");
            // Você pode optar por lançar uma exceção ou apenas logar um erro aqui.
        }
    }
}
