package com.pokemontcg.matches.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "matches")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "current_phase", length = 30)
    private String currentPhase;

    @Column(name = "turn_number", nullable = false)
    private Integer turnNumber = 0;

    @Column(name = "current_player_id")
    private UUID currentPlayerId;

    @Column(name = "first_player_id")
    private UUID firstPlayerId;

    @Column(name = "winner_player_id")
    private UUID winnerPlayerId;

    @Column(name = "finish_reason", length = 60)
    private String finishReason;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @Column(name = "latest_state_version")
    private Long latestStateVersion;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MatchPlayerEntity> players = new ArrayList<>();

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MatchStateEntity> states = new ArrayList<>();

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MatchLogEntity> logs = new ArrayList<>();

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
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCurrentPhase() { return currentPhase; }
    public void setCurrentPhase(String currentPhase) { this.currentPhase = currentPhase; }
    public Integer getTurnNumber() { return turnNumber; }
    public void setTurnNumber(Integer turnNumber) { this.turnNumber = turnNumber; }
    public UUID getCurrentPlayerId() { return currentPlayerId; }
    public void setCurrentPlayerId(UUID currentPlayerId) { this.currentPlayerId = currentPlayerId; }
    public UUID getFirstPlayerId() { return firstPlayerId; }
    public void setFirstPlayerId(UUID firstPlayerId) { this.firstPlayerId = firstPlayerId; }
    public UUID getWinnerPlayerId() { return winnerPlayerId; }
    public void setWinnerPlayerId(UUID winnerPlayerId) { this.winnerPlayerId = winnerPlayerId; }
    public String getFinishReason() { return finishReason; }
    public void setFinishReason(String finishReason) { this.finishReason = finishReason; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public Instant getFinishedAt() { return finishedAt; }
    public void setFinishedAt(Instant finishedAt) { this.finishedAt = finishedAt; }
    public Long getLatestStateVersion() { return latestStateVersion; }
    public void setLatestStateVersion(Long latestStateVersion) { this.latestStateVersion = latestStateVersion; }
    public List<MatchPlayerEntity> getPlayers() { return players; }
    public List<MatchStateEntity> getStates() { return states; }
    public List<MatchLogEntity> getLogs() { return logs; }
}