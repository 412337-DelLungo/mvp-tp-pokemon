package com.pokemontcg.matches.api.dto;

public record MatchResponse(
        String matchId,
        String playerId,
        String side,
        String status
) {
}