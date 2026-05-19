package com.pokemontcg.cards.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "card_attacks")
public class CardAttackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private CardEntity card;

    @Column(name = "attack_index", nullable = false)
    private Integer attackIndex;

    @Column(name = "name", nullable = false, length = 160)
    private String name;

    @Column(name = "printed_cost", columnDefinition = "text[]")
    private String[] printedCost;

    @Column(name = "converted_energy_cost", nullable = false)
    private Integer convertedEnergyCost = 0;

    @Column(name = "damage_text", length = 40)
    private String damageText;

    @Column(name = "base_damage")
    private Integer baseDamage;

    @Column(name = "effect_text", columnDefinition = "text")
    private String effectText;

    @Column(name = "effect_code", length = 80)
    private String effectCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public CardEntity getCard() { return card; }
    public void setCard(CardEntity card) { this.card = card; }
    public Integer getAttackIndex() { return attackIndex; }
    public void setAttackIndex(Integer attackIndex) { this.attackIndex = attackIndex; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String[] getPrintedCost() { return printedCost; }
    public void setPrintedCost(String[] printedCost) { this.printedCost = printedCost; }
    public Integer getConvertedEnergyCost() { return convertedEnergyCost; }
    public void setConvertedEnergyCost(Integer convertedEnergyCost) { this.convertedEnergyCost = convertedEnergyCost; }
    public String getDamageText() { return damageText; }
    public void setDamageText(String damageText) { this.damageText = damageText; }
    public Integer getBaseDamage() { return baseDamage; }
    public void setBaseDamage(Integer baseDamage) { this.baseDamage = baseDamage; }
    public String getEffectText() { return effectText; }
    public void setEffectText(String effectText) { this.effectText = effectText; }
    public String getEffectCode() { return effectCode; }
    public void setEffectCode(String effectCode) { this.effectCode = effectCode; }
}