package com.pokemontcg.cards.domain;

public class TrainerCardDefinition extends CardDefinition {
    private String trainerSubtype;
    private boolean isAceSpec;
    private String effectCode;

    public String getTrainerSubtype() { return trainerSubtype; }
    public boolean isAceSpec() { return isAceSpec; }
    public String getEffectCode() { return effectCode; }
}