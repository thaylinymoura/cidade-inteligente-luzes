package com.cidade.inteligente.cidade_inteligente.observer;

import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

public class PainelDeControle implements  AtualizadorPoste {
    @Override
    public void atualizar(PosteDeLuz poste) {
        System.out.println("[Painel de Controle] Poste '" + poste.getId() + "' estado: " +
                (poste.isLigado() ? "LIGADO" : "DESLIGADO"));

    }
}
