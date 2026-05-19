package com.pokemontcg.engine.ports;

import com.pokemontcg.engine.event.GameEvent;

public interface EventPublisherPort {
    void publishPublicEvent(GameEvent event);
    void publishPrivateEvent(String playerId, GameEvent event);
}