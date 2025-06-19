package com.cidade.inteligente.cidade_inteligente.observer;

public interface SujeitoPoste {
    void adicionarObservador(AtualizadorPoste observador);
    void removerObservador(AtualizadorPoste observador);
    void notificarObservadores();

}
