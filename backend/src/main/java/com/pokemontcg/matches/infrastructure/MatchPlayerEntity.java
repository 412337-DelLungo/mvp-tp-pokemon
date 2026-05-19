package com.pokemontcg.matches.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "match_players")
public class MatchPlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private MatchEntity match;

    @Column(name = "player_id", nullable = false)
    private UUID playerId;

    @Column(name = "player_kind", nullable = false, length = 20)
    private String playerKind;

    @Column(name = "side", nullable = false, length = 30)
    private String side;

    @Column(name = "deck_id")
    private UUID deckId;

    @Column(name = "display_name", nullable = false, length = 80)
    private String displayName;

    @Column(name = "joined_at", nullable = false)
    private Instant joinedAt;

    @PrePersist
    protected void onCreate() {
        joinedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public MatchEntity getMatch() { return match; }
    public void setMatch(MatchEntity match) { this.match = match; }
    public UUID getPlayerId() { return playerId; }
    public void setPlayerId(UUID playerId) { this.playerId = playerId; }
    public String getPlayerKind() { return playerKind; }
    public void setPlayerKind(String playerKind) { this.playerKind = playerKind; }
    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }
    public UUID getDeckId() { return deckId; }
    public void setDeckId(UUID deckId) { this.deckId = deckId; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public Instant getJoinedAt() { return joinedAt; }
}