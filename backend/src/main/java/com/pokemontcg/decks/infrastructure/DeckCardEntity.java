package com.pokemontcg.decks.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "deck_cards")
public class DeckCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id", nullable = false)
    private DeckEntity deck;

    @Column(name = "card_id", nullable = false, length = 80)
    private String cardId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public DeckEntity getDeck() { return deck; }
    public void setDeck(DeckEntity deck) { this.deck = deck; }
    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Instant getCreatedAt() { return createdAt; }
}