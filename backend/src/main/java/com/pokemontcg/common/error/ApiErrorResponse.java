package com.pokemontcg.common.error;

import java.time.Instant;

public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String code,
        String message,
        String path,
        Object details
) {
    public static ApiErrorResponse of(int status, String error, String code, String message, String path) {
        return new ApiErrorResponse(Instant.now(), status, error, code, message, path, null);
    }

    public static ApiErrorResponse of(int status, String error, String code, String message, String path, Object details) {
        return new ApiErrorResponse(Instant.now(), status, error, code, message, path, details);
    }
}