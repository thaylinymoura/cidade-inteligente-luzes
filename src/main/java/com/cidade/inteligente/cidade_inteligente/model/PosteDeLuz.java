package com.cidade.inteligente.cidade_inteligente.model;
import com.cidade.inteligente.cidade_inteligente.factory.Lampada;
import com.cidade.inteligente.cidade_inteligente.factory.ModuloComunicacao;
import com.cidade.inteligente.cidade_inteligente.factory.Sensor;
import com.cidade.inteligente.cidade_inteligente.observer.AtualizadorPoste;
import com.cidade.inteligente.cidade_inteligente.observer.SujeitoPoste;
import com.cidade.inteligente.cidade_inteligente.strategy.ComportamentoLigamento;

import java.util.ArrayList;
import java.util.List;

public class PosteDeLuz implements SujeitoPoste {

    private boolean ligado;
    private String id; // Para identificar o poste

    // Atributos para os componentes do Abstract Factory
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
        this.comportamento = comportamentoInicial; // Inicializa a estratégia
        this.ligado = false; // Começa desligado
        this.observadores = new ArrayList<>(); // Inicializa para uso futuro (Parte 3)
        System.out.println("Poste de Luz '" + id + "' criado com estratégia: " + comportamentoInicial.getClass().getSimpleName());
    }

    // Estes são os métodos que o HelloController espera encontrar.
    public void ligar() {
        if (!this.ligado) {
            this.ligado = true;
            // A chamada a lampada.ligar() e notificarObservadores() será implementada nas próximas partes.
            // Por enquanto, apenas o System.out.println é o suficiente.
            System.out.println("Poste " + id + " LIGADO.");
        }
    }

    public void desligar() {
        if (this.ligado) {
            this.ligado = false;
            // A chamada a lampada.desligar() e notificarObservadores() será implementada nas próximas partes.
            System.out.println("Poste " + id + " DESLIGADO.");
        }
    }


    public boolean isLigado() {
        return ligado;
    }


    public void setLigado(boolean ligado) {
        if (this.ligado != ligado) { // Só notifica se o estado mudou
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


    // NOVO: Método para mudar a estratégia em tempo de execução
    public void setComportamentoLigamento(ComportamentoLigamento novoComportamento) {
        this.comportamento = novoComportamento;
        System.out.println("Comportamento de ligamento do poste " + id + " alterado para: " + novoComportamento.getClass().getSimpleName());
    }

    // NOVO: Método para executar a estratégia atual
    public void executarComportamento() {
        if (comportamento != null) {
            comportamento.avaliar(this); // Delega a decisão de ligar/desligar para a estratégia
        }
    }

    // Métodos da interface SujeitoPoste
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
