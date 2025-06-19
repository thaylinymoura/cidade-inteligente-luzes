package com.cidade.inteligente.cidade_inteligente.strategy;

import com.cidade.inteligente.cidade_inteligente.model.PosteDeLuz;

public class EstrategiaManual implements ComportamentoLigamento{
    @Override
    public void avaliar(PosteDeLuz poste) {
        // Este comportamento não faz nada automaticamente.
        // O controle de ligar/desligar é feito externamente (e.g., pelo usuário na UI).
        // É útil para representar postes que não possuem automação.
    }
}
