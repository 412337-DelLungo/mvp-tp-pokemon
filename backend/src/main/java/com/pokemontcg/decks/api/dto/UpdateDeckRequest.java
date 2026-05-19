package com.pokemontcg.decks.api.dto;

import java.util.List;

public record UpdateDeckRequest(
        String name,
        List<CreateDeckRequest.DeckCardRequest> cards
) {
}