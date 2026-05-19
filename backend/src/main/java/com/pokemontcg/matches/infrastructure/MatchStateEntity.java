package com.pokemontcg.matches.infrastructure;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "match_states")
public class MatchStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private MatchEntity match;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "serialized_state", nullable = false, columnDefinition = "jsonb")
    private String serializedState;

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
    public String getSerializedState() { return serializedState; }
    public void setSerializedState(String serializedState) { this.serializedState = serializedState; }
    public Instant getCreatedAt() { return createdAt; }
}