package com.pokemontcg.cards.api.dto;

public record CardSearchRequest(
        String query,
        String supertype,
        String setCode,
        Integer page,
        Integer size
) {
}