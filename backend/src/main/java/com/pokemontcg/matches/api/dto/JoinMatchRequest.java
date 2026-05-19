package com.pokemontcg.matches.api.dto;

public record JoinMatchRequest(
        String playerName,
        String deckId
) {
}