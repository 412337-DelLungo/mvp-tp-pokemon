package com.pokemontcg.cards.infrastructure;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "card_resistances")
public class CardResistanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private CardEntity card;

    @Column(name = "energy_type", nullable = false, length = 30)
    private String energyType;

    @Column(name = "value", nullable = false)
    private Integer value = -20;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public CardEntity getCard() { return card; }
    public void setCard(CardEntity card) { this.card = card; }
    public String getEnergyType() { return energyType; }
    public void setEnergyType(String energyType) { this.energyType = energyType; }
    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }
}