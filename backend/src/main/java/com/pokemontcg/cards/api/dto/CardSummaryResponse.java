package com.pokemontcg.cards.api.dto;

public record CardSummaryResponse(
        String id,
        String name,
        String supertype,
        String setCode,
        String number,
        String imageSmallUrl
) {
}