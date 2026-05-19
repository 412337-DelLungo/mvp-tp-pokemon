package com.pokemontcg.decks.api.dto;

import java.util.List;

public record DeckResponse(
        String id,
        String name,
        String ownerPlayerId,
        String source,
        int totalCards,
        boolean valid,
        List<DeckCardResponse> cards,
        DeckValidationResponse validation
) {
}