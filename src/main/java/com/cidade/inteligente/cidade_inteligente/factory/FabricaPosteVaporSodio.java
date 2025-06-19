package com.cidade.inteligente.cidade_inteligente.factory;

public class FabricaPosteVaporSodio  implements  FabricaPoste{

    @Override
    public Lampada criarLampada() {
        return new LampadaVaporSodio();
    }

    @Override
    public Sensor criarSensor() {
        return null; // Poste simples, sem sensor
    }

    @Override
    public ModuloComunicacao criarModuloComunicacao() {
        return null; // Poste simples, sem módulo de comunicação
    }

}


