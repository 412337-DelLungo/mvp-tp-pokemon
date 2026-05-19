package com.pokemontcg.engine;

import com.pokemontcg.engine.action.ActionResult;
import com.pokemontcg.engine.action.GameAction;
import com.pokemontcg.engine.model.GameState;
import com.pokemontcg.engine.ports.CardLookupPort;
import com.pokemontcg.engine.ports.EventPublisherPort;
import com.pokemontcg.engine.ports.RandomizerPort;
import com.pokemontcg.engine.ports.StatePersisterPort;

import java.util.UUID;

public class GameEngine {
    private final CardLookupPort cardLookup;
    private final RandomizerPort randomizer;
    private final StatePersisterPort persister;
    private final EventPublisherPort eventPublisher;

    public GameEngine(CardLookupPort cardLookup, RandomizerPort randomizer,
                      StatePersisterPort persister, EventPublisherPort eventPublisher) {
        this.cardLookup = cardLookup;
        this.randomizer = randomizer;
        this.persister = persister;
        this.eventPublisher = eventPublisher;
    }

    public ActionResult applyAction(UUID matchId, UUID playerId, GameAction action) {
        return null;
    }

    public GameState loadState(UUID matchId) {
        return null;
    }
}