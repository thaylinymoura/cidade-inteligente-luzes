package com.cidade.inteligente.cidade_inteligente.factory;

import com.cidade.inteligente.cidade_inteligente.modulos.ModuloComunicacao;

public interface FabricaPoste {

    Lampada criarLampada();
    Sensor criarSensor();
    ModuloComunicacao criarModuloComunicacao();
}
