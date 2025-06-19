package com.cidade.inteligente.cidade_inteligente.factory;

public interface FabricaPoste {

    Lampada criarLampada();
    Sensor criarSensor();
    ModuloComunicacao criarModuloComunicacao();
}
