package com.pokemontcg.common.ids;

import java.util.UUID;

public record CardInstanceId(UUID value) {
    public static CardInstanceId create() {
        return new CardInstanceId(UUID.randomUUID());
    }

    public static CardInstanceId of(String value) {
        return new CardInstanceId(UUID.fromString(value));
    }

    public static CardInstanceId of(UUID value) {
        return new CardInstanceId(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}