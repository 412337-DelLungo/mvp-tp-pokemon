package com.pokemontcg.matches.api.dto;

public record CreateMatchRequest(
        String playerName,
        String deckId
) {
}