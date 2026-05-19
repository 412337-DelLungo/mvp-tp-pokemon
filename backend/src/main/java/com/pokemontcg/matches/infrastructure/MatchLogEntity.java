package com.pokemontcg.matches.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "match_logs")
public class MatchLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private MatchEntity match;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber;

    @Column(name = "player_id")
    private UUID playerId;

    @Column(name = "action_type", length = 80)
    private String actionType;

    @Column(name = "event_type", nullable = false, length = 80)
    private String eventType;

    @Column(name = "result", nullable = false, length = 30)
    private String result;

    @Column(name = "message", columnDefinition = "text")
    private String message;

    @Column(name = "payload", columnDefinition = "jsonb")
    private String payload = "{}";

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public MatchEntity getMatch() { return match; }
    public void setMatch(MatchEntity match) { this.match = match; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
    public Integer getTurnNumber() { return turnNumber; }
    public void setTurnNumber(Integer turnNumber) { this.turnNumber = turnNumber; }
    public UUID getPlayerId() { return playerId; }
    public void setPlayerId(UUID playerId) { this.playerId = playerId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
    public Instant getCreatedAt() { return createdAt; }
}