package com.pokemontcg.common.error;

public class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super("NOT_FOUND", message);
    }
}