package com.cidade.inteligente.cidade_inteligente.factory;

import com.cidade.inteligente.cidade_inteligente.modulos.ModuloComunicacao;
import com.cidade.inteligente.cidade_inteligente.modulos.ModuloWifi;

public class FabricaPosteLED implements FabricaPoste{

    @Override
    public Lampada criarLampada() {
        return new LampadaLED();
    }

    @Override
    public Sensor criarSensor() {
        return new SensorPresenca();
    }

    @Override
    public ModuloComunicacao criarModuloComunicacao() {
        return new ModuloWifi();
    }

}


