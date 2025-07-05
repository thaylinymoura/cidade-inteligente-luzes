package com.cidade.inteligente.cidade_inteligente.strategy;

import com.cidade.inteligente.cidade_inteligente.factory.Sensor;
import com.cidade.inteligente.cidade_inteligente.factory.SensorLuminosidade;
import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

public class EstrategiaPorLuminosidade implements ComportamentoLigamento {
    private double limiarLuminosidade;

    public EstrategiaPorLuminosidade(double limiarLuminosidade) {
        this.limiarLuminosidade = limiarLuminosidade;
    }

    @Override
    public void avaliar(PosteDeLuz poste) {
        Sensor sensor = poste.getSensor();


        if (sensor != null && sensor instanceof SensorLuminosidade) {
            double luminosidadeAtual = sensor.medirLuminosidade();
            System.out.println("Poste " + poste.getId() + " - Luminosidade atual: " + luminosidadeAtual + " Lux.");

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

        }
    }
}
