package com.cidade.inteligente.cidade_inteligente.observer;

import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

public class CentroDeMonitoramento implements  AtualizadorPoste {

    @Override
    public void atualizar(PosteDeLuz poste) {
        System.out.println("[Centro de Monitoramento] Registrando evento para Poste '" +
                poste.getId() + "': " + (poste.isLigado() ? "Ligou" : "Desligou"));

}

}
