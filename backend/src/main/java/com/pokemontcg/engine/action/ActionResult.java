package com.pokemontcg.engine.action;

import com.pokemontcg.engine.event.GameEvent;
import com.pokemontcg.engine.model.PrivatePlayerState;
import com.pokemontcg.engine.model.PublicGameState;

import java.util.List;

public class ActionResult {
    private boolean success;
    private String clientRequestId;
    private PublicGameState publicState;
    private PrivatePlayerState privateState;
    private List<GameEvent> events;
    private GameError error;

    public boolean isSuccess() { return success; }
    public String getClientRequestId() { return clientRequestId; }
    public PublicGameState getPublicState() { return publicState; }
    public PrivatePlayerState getPrivateState() { return privateState; }
    public List<GameEvent> getEvents() { return events; }
    public GameError getError() { return error; }
}