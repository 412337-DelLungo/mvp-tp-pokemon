package com.pokemontcg.cards.domain;

import java.util.List;

public class PokemonCardDefinition extends CardDefinition {
    private int hp;
    private String stage;
    private String evolvesFrom;
    private List<String> types;
    private List<AttackDefinition> attacks;
    private List<WeaknessDefinition> weaknesses;
    private List<ResistanceDefinition> resistances;
    private List<String> retreatCost;
    private boolean isEx;
    private boolean isMega;

    public int getHp() { return hp; }
    public String getStage() { return stage; }
    public String getEvolvesFrom() { return evolvesFrom; }
    public List<String> getTypes() { return types; }
    public List<AttackDefinition> getAttacks() { return attacks; }
    public List<WeaknessDefinition> getWeaknesses() { return weaknesses; }
    public List<ResistanceDefinition> getResistances() { return resistances; }
    public List<String> getRetreatCost() { return retreatCost; }
    public boolean isEx() { return isEx; }
    public boolean isMega() { return isMega; }

    public static class AttackDefinition {
        private int index;
        private String name;
        private List<String> cost;
        private String damage;
        private String text;
    }

    public static class WeaknessDefinition {
        private String type;
        private String value;
    }

    public static class ResistanceDefinition {
        private String type;
        private String value;
    }
}