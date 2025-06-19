package com.cidade.inteligente.cidade_inteligente.strategy;

import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

import java.time.LocalTime;

public class EstrategiaPorHorario implements ComportamentoLigamento{
    private int horaLigamento;
    private int horaDesligamento;

    public EstrategiaPorHorario(int horaLigamento, int horaDesligamento) {
        this.horaLigamento = horaLigamento;
        this.horaDesligamento = horaDesligamento;
    }

    @Override
    public void avaliar(PosteDeLuz poste) {
        LocalTime horaAtual = LocalTime.now();
        int hora = horaAtual.getHour(); // Obtém a hora atual (0-23)

        // Lógica: ligar entre horaLigamento e horaDesligamento (ex: 18h e 6h do dia seguinte)
        // Considera o cenário noturno que passa pela meia-noite
        boolean deveEstarLigado = (hora >= horaLigamento || hora < horaDesligamento);

        if (deveEstarLigado && !poste.isLigado()) {
            poste.ligar();
        } else if (!deveEstarLigado && poste.isLigado()) {
            poste.desligar();
        }
    }
}
