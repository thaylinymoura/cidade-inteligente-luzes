package com.cidade.inteligente.cidade_inteligente.factory;

public class SensorLuminosidade  implements  Sensor{
    @Override
    public boolean detectarMovimento() {
        return false; // Não detecta movimento
    }

    @Override
    public double medirLuminosidade() {
        // Simulação de luminosidade (valores entre 0 e 500 Lux)
        return Math.random() * 500;
    }
}

