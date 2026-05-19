package com.pokemontcg.decks.api.dto;

import java.util.List;

public record CreateDeckRequest(
        String name,
        List<DeckCardRequest> cards
) {
    public record DeckCardRequest(String cardId, int quantity) {}
}