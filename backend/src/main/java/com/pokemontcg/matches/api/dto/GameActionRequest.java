package com.pokemontcg.matches.api.dto;

public record GameActionRequest(
        String type,
        String playerId,
        Object payload,
        String clientRequestId
) {
}