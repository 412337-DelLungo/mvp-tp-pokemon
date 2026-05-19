package com.pokemontcg.cards.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
public class CardEntity {

    @Id
    @Column(name = "id", length = 80)
    private String id;

    @Column(name = "name", nullable = false, length = 160)
    private String name;

    @Column(name = "supertype", nullable = false, length = 30)
    private String supertype;

    @Column(name = "subtypes", columnDefinition = "text[]")
    private String[] subtypes;

    @Column(name = "set_code", nullable = false, length = 30)
    private String setCode;

    @Column(name = "number", length = 30)
    private String number;

    @Column(name = "rarity", length = 80)
    private String rarity;

    @Column(name = "image_small_url", columnDefinition = "text")
    private String imageSmallUrl;

    @Column(name = "image_large_url", columnDefinition = "text")
    private String imageLargeUrl;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "pokemon_stage", length = 30)
    private String pokemonStage;

    @Column(name = "evolves_from", length = 160)
    private String evolvesFrom;

    @Column(name = "pokemon_types", columnDefinition = "text[]")
    private String[] pokemonTypes;

    @Column(name = "retreat_cost", columnDefinition = "text[]")
    private String[] retreatCost;

    @Column(name = "converted_retreat_cost")
    private Integer convertedRetreatCost;

    @Column(name = "is_ex", nullable = false)
    private Boolean isEx = false;

    @Column(name = "is_mega", nullable = false)
    private Boolean isMega = false;

    @Column(name = "energy_card_type", length = 30)
    private String energyCardType;

    @Column(name = "provides_energy_types", columnDefinition = "text[]")
    private String[] providesEnergyTypes;

    @Column(name = "trainer_subtype", length = 30)
    private String trainerSubtype;

    @Column(name = "is_ace_spec", nullable = false)
    private Boolean isAceSpec = false;

    @Column(name = "rules_text", columnDefinition = "text[]")
    private String[] rulesText;

    @Column(name = "raw_json", columnDefinition = "jsonb")
    private String rawJson;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CardAttackEntity> attacks = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CardWeaknessEntity> weaknesses = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CardResistanceEntity> resistances = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSupertype() { return supertype; }
    public void setSupertype(String supertype) { this.supertype = supertype; }
    public String[] getSubtypes() { return subtypes; }
    public void setSubtypes(String[] subtypes) { this.subtypes = subtypes; }
    public String getSetCode() { return setCode; }
    public void setSetCode(String setCode) { this.setCode = setCode; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getRarity() { return rarity; }
    public void setRarity(String rarity) { this.rarity = rarity; }
    public String getImageSmallUrl() { return imageSmallUrl; }
    public void setImageSmallUrl(String imageSmallUrl) { this.imageSmallUrl = imageSmallUrl; }
    public String getImageLargeUrl() { return imageLargeUrl; }
    public void setImageLargeUrl(String imageLargeUrl) { this.imageLargeUrl = imageLargeUrl; }
    public Integer getHp() { return hp; }
    public void setHp(Integer hp) { this.hp = hp; }
    public String getPokemonStage() { return pokemonStage; }
    public void setPokemonStage(String pokemonStage) { this.pokemonStage = pokemonStage; }
    public String getEvolvesFrom() { return evolvesFrom; }
    public void setEvolvesFrom(String evolvesFrom) { this.evolvesFrom = evolvesFrom; }
    public String[] getPokemonTypes() { return pokemonTypes; }
    public void setPokemonTypes(String[] pokemonTypes) { this.pokemonTypes = pokemonTypes; }
    public String[] getRetreatCost() { return retreatCost; }
    public void setRetreatCost(String[] retreatCost) { this.retreatCost = retreatCost; }
    public Integer getConvertedRetreatCost() { return convertedRetreatCost; }
    public void setConvertedRetreatCost(Integer convertedRetreatCost) { this.convertedRetreatCost = convertedRetreatCost; }
    public Boolean getIsEx() { return isEx; }
    public void setIsEx(Boolean isEx) { this.isEx = isEx; }
    public Boolean getIsMega() { return isMega; }
    public void setIsMega(Boolean isMega) { this.isMega = isMega; }
    public String getEnergyCardType() { return energyCardType; }
    public void setEnergyCardType(String energyCardType) { this.energyCardType = energyCardType; }
    public String[] getProvidesEnergyTypes() { return providesEnergyTypes; }
    public void setProvidesEnergyTypes(String[] providesEnergyTypes) { this.providesEnergyTypes = providesEnergyTypes; }
    public String getTrainerSubtype() { return trainerSubtype; }
    public void setTrainerSubtype(String trainerSubtype) { this.trainerSubtype = trainerSubtype; }
    public Boolean getIsAceSpec() { return isAceSpec; }
    public void setIsAceSpec(Boolean isAceSpec) { this.isAceSpec = isAceSpec; }
    public String[] getRulesText() { return rulesText; }
    public void setRulesText(String[] rulesText) { this.rulesText = rulesText; }
    public String getRawJson() { return rawJson; }
    public void setRawJson(String rawJson) { this.rawJson = rawJson; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public List<CardAttackEntity> getAttacks() { return attacks; }
    public List<CardWeaknessEntity> getWeaknesses() { return weaknesses; }
    public List<CardResistanceEntity> getResistances() { return resistances; }
}