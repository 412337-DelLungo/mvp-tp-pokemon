package com.pokemontcg.common.ids;

import java.util.UUID;

public record PlayerId(UUID value) {
    public static PlayerId create() {
        return new PlayerId(UUID.randomUUID());
    }

    public static PlayerId of(String value) {
        return new PlayerId(UUID.fromString(value));
    }

    public static PlayerId of(UUID value) {
        return new PlayerId(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}