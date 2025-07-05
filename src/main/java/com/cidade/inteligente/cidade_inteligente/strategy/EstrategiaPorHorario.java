package com.cidade.inteligente.cidade_inteligente.strategy;

import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

import java.time.LocalTime;

public class EstrategiaPorHorario implements ComportamentoLigamento {
    private int horaLigamento;
    private int horaDesligamento;

    public EstrategiaPorHorario(int horaLigamento, int horaDesligamento) {
        this.horaLigamento = horaLigamento;
        this.horaDesligamento = horaDesligamento;
    }

    @Override
    public void avaliar(PosteDeLuz poste) {
        LocalTime horaAtual = LocalTime.now();
        int hora = horaAtual.getHour();
        boolean deveEstarLigado = (hora >= horaLigamento || hora < horaDesligamento);

        if (deveEstarLigado && !poste.isLigado()) {
            poste.ligar();
        } else if (!deveEstarLigado && poste.isLigado()) {
            poste.desligar();
        }
    }
}
