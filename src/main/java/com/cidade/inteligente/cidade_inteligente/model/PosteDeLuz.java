package com.cidade.inteligente.cidade_inteligente.model;
import com.cidade.inteligente.cidade_inteligente.factory.Lampada;
import com.cidade.inteligente.cidade_inteligente.modulos.ModuloComunicacao;
import com.cidade.inteligente.cidade_inteligente.factory.Sensor;
import com.cidade.inteligente.cidade_inteligente.observer.AtualizadorPoste;
import com.cidade.inteligente.cidade_inteligente.observer.SujeitoPoste;
import com.cidade.inteligente.cidade_inteligente.strategy.ComportamentoLigamento;

import java.util.ArrayList;
import java.util.List;

public class PosteDeLuz implements SujeitoPoste {

    private boolean ligado;
    private String id;


    private Lampada lampada;
    private Sensor sensor;
    private ModuloComunicacao moduloComunicacao;

    private ComportamentoLigamento comportamento;

    private List<AtualizadorPoste> observadores = new ArrayList<>();

    public PosteDeLuz(String id, Lampada lampada, Sensor sensor, ModuloComunicacao moduloComunicacao, ComportamentoLigamento comportamentoInicial) {
        this.id = id;
        this.lampada = lampada;
        this.sensor = sensor;
        this.moduloComunicacao = moduloComunicacao;
        this.comportamento = comportamentoInicial;
        this.ligado = false;
        this.observadores = new ArrayList<>();
        System.out.println("Poste de Luz '" + id + "' criado com estratégia: " + comportamentoInicial.getClass().getSimpleName());
    }

    // Estes são os métodos que o HelloController espera encontrar.
    public void ligar() {
        if (!this.ligado) {
            this.ligado = true;

            System.out.println("Poste " + id + " LIGADO.");
        }
    }

    public void desligar() {
        if (this.ligado) {
            this.ligado = false;

            System.out.println("Poste " + id + " DESLIGADO.");
        }
    }


    public boolean isLigado() {
        return ligado;
    }


    public void setLigado(boolean ligado) {
        if (this.ligado != ligado) {
            this.ligado = ligado;
            notificarObservadores();
        }
    }

    public String getId() {
        return id;
    }

// NOVOS GETTERS para os componentes do poste
    public Lampada getLampada() {
        return lampada;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public ModuloComunicacao getModuloComunicacao() {
        return moduloComunicacao;
    }

    public ComportamentoLigamento getComportamento() {
        return comportamento;
    }



    public void setComportamentoLigamento(ComportamentoLigamento novoComportamento) {
        this.comportamento = novoComportamento;
        System.out.println("Comportamento de ligamento do poste " + id + " alterado para: " + novoComportamento.getClass().getSimpleName());
    }


    public void executarComportamento() {
        if (comportamento != null) {
            comportamento.avaliar(this); // Delega a decisão de ligar/desligar para a estratégia
        }
    }


    @Override
    public void adicionarObservador(AtualizadorPoste observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(AtualizadorPoste observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        for (AtualizadorPoste observador : observadores) {
            observador.atualizar(this);
        }
    }

}
