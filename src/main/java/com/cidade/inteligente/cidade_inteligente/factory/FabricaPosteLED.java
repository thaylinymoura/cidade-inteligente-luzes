package com.cidade.inteligente.cidade_inteligente.factory;

public class FabricaPosteLED implements FabricaPoste{

    @Override
    public Lampada criarLampada() {
        return new LampadaLED();
    }

    @Override
    public Sensor criarSensor() {
        return new SensorPresenca(); // Exemplo: Poste LED com sensor de presença
    }

    @Override
    public ModuloComunicacao criarModuloComunicacao() {
        return new ModuloWifi(); // Exemplo: Poste LED com comunicação Wi-Fi
    }

}


