package com.pokemontcg.decks.api.dto;

public record DeckCardResponse(
        String cardId,
        String name,
        int quantity,
        String supertype,
        boolean isBasicEnergy
) {
}