package com.pokemontcg.decks.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "decks")
public class DeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "owner_user_id")
    private UUID ownerUserId;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "source", length = 30)
    private String source = "USER";

    @Column(name = "valid", nullable = false)
    private Boolean valid = false;

    @Column(name = "validation_errors", columnDefinition = "jsonb")
    private String validationErrors = "[]";

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private java.util.List<DeckCardEntity> cards = new java.util.ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getOwnerUserId() { return ownerUserId; }
    public void setOwnerUserId(UUID ownerUserId) { this.ownerUserId = ownerUserId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Boolean getValid() { return valid; }
    public void setValid(Boolean valid) { this.valid = valid; }
    public String getValidationErrors() { return validationErrors; }
    public void setValidationErrors(String validationErrors) { this.validationErrors = validationErrors; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public java.util.List<DeckCardEntity> getCards() { return cards; }
}