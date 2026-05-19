package com.pokemontcg.engine.ports;

import com.pokemontcg.cards.domain.CardDefinition;

public interface CardLookupPort {
    CardDefinition getCardById(String cardId);
}