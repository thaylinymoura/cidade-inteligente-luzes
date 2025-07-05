package com.cidade.inteligente.cidade_inteligente.factory;

public class SensorLuminosidade implements Sensor {
    @Override
    public boolean detectarMovimento() {
        return false;
    }

    @Override
    public double medirLuminosidade() {

        return Math.random() * 500;
    }
}
