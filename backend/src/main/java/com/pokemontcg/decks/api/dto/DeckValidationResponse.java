package com.pokemontcg.decks.api.dto;

import java.util.List;

public record DeckValidationResponse(
        boolean valid,
        List<DeckValidationError> errors
) {
    public record DeckValidationError(String code, String message, Object details) {}
}