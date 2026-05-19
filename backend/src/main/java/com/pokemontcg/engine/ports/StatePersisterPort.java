package com.pokemontcg.engine.ports;

import com.pokemontcg.engine.model.GameState;

import java.util.UUID;

public interface StatePersisterPort {
    void saveState(UUID matchId, GameState state);
    GameState loadState(UUID matchId);
}