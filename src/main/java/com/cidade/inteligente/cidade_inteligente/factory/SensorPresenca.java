package com.cidade.inteligente.cidade_inteligente.factory;

public class SensorPresenca implements  Sensor {

        @Override
        public boolean detectarMovimento() {
            return Math.random() < 0.1;
        }

        @Override
        public double medirLuminosidade() {
            return 0.0;
        }

    }