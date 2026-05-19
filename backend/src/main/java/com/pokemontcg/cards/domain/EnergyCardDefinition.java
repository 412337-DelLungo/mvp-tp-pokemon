package com.pokemontcg.cards.domain;

import java.util.List;

public class EnergyCardDefinition extends CardDefinition {
    private String energyCardType;
    private List<String> provides;

    public String getEnergyCardType() { return energyCardType; }
    public List<String> getProvides() { return provides; }
}