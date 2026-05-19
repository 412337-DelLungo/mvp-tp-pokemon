package com.pokemontcg.common.ids;

public record CardId(String value) {
    public static CardId of(String value) {
        return new CardId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}