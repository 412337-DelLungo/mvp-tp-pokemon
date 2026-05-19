package com.pokemontcg.cards.domain;

import java.util.List;

public abstract class CardDefinition {
    protected String id;
    protected String name;
    protected String supertype;
    protected List<String> subtypes;
    protected String setCode;
    protected String number;
    protected String imageSmallUrl;
    protected String imageLargeUrl;
    protected List<String> rulesText;

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSupertype() { return supertype; }
    public List<String> getSubtypes() { return subtypes; }
    public String getSetCode() { return setCode; }
    public String getNumber() { return number; }
    public String getImageSmallUrl() { return imageSmallUrl; }
    public String getImageLargeUrl() { return imageLargeUrl; }
    public List<String> getRulesText() { return rulesText; }
}