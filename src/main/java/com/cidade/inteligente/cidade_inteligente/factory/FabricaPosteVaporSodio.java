package com.cidade.inteligente.cidade_inteligente.factory;

import com.cidade.inteligente.cidade_inteligente.modulos.ModuloComunicacao;

public class FabricaPosteVaporSodio  implements  FabricaPoste{

    @Override
    public Lampada criarLampada() {
        return new LampadaVaporSodio();
    }

    @Override
    public Sensor criarSensor() {
        return null;
    }

    @Override
    public ModuloComunicacao criarModuloComunicacao() {
        return null;
    }

}


