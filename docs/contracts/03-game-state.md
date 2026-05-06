# GameState Contract v1

## Objetivo

`GameState` representa el estado interno completo de una partida.

Solo vive en backend.

No debe enviarse directamente al frontend porque contiene información oculta:

- mano de ambos jugadores,
- orden de los mazos,
- cartas de Premio,
- requests internos,
- datos completos para reconstrucción.

## GameState

```json
{
  "schemaVersion": 1,
  "gameId": "game-123",
  "stateVersion": 1,
  "status": "ACTIVE",
  "createdAt": "2026-05-05T19:00:00Z",
  "updatedAt": "2026-05-05T19:02:00Z",
  "players": [],
  "turn": {},
  "stadiumCardInstanceId": null,
  "pendingRequest": null,
  "winner": null,
  "suddenDeath": {
    "isSuddenDeath": false,
    "roundNumber": 0,
    "prizeCardsPerPlayer": 6
  }
}
```

## **PlayerState**

```json
{
  "playerId": "player-1",
  "displayName": "Player 1",
  "deckId": "deck-1",
  "deck": [],
  "hand": [],
  "prizeCards": [],
  "discardPile": [],
  "lostZone": [],
  "board": {},
  "setup": {},
  "connection": {}
}
```

## **CardInstance**

```json
{
  "cardInstanceId": "ci-001",
  "cardId": "xy1-1",
  "ownerPlayerId": "player-1",
  "currentZone": "HAND",
  "isFaceDown": false
}
```

## **BoardState**

```json
{
  "active": null,
  "bench": []
}
```

## **PokemonInPlay**

```json
{
  "pokemonId": "p-001",
  "ownerPlayerId": "player-1",
  "cardInstanceId": "ci-100",
  "cardId": "xy1-1",
  "zone": "ACTIVE",
  "benchSlot": null,
  "damageCounters": 0,
  "attachedEnergyCardInstanceIds": [],
  "attachedToolCardInstanceId": null,
  "evolutionCardInstanceIds": [],
  "specialConditions": [],
  "enteredPlayTurn": 1,
  "lastEvolvedTurn": null,
  "evolvedThisTurn": false,
  "temporaryEffects": [],
  "abilityStates": []
}
```

## **SpecialConditionState**

```json
{
  "condition": "POISONED",
  "appliedTurn": 3,
  "sourceCardInstanceId": "ci-555",
  "sourceAttackId": "xy1-1-attack-1"
}
```

## **TurnState**

```json
{
  "turnNumber": 3,
  "currentPlayerId": "player-1",
  "firstPlayerId": "player-1",
  "phase": "MAIN",
  "isFirstTurnOfGame": false,
  "flags": {
    "hasDrawnThisTurn": true,
    "hasAttachedEnergyThisTurn": false,
    "hasRetreatedThisTurn": false,
    "hasPlayedSupporterThisTurn": false,
    "hasPlayedStadiumThisTurn": false,
    "hasAttackedThisTurn": false
  }
}
```

## **SetupState**

```json
{
  "hasConfirmedInitialActive": true,
  "hasConfirmedInitialBench": true,
  "mulliganCount": 0,
  "canDrawMulliganBonusCards": 0,
  "setupComplete": true
}
```

## **ConnectionState**

```json
{
  "isConnected": true,
  "lastSeenAt": "2026-05-05T19:02:00Z"
}
```

## **PendingRequest**

```json
{
  "requestId": "req-001",
  "type": "CHOOSE_PRIZE_CARD",
  "playerId": "player-1",
  "sourceActionId": "a-010",
  "required": true,
  "minChoices": 1,
  "maxChoices": 1,
  "validOptions": [
    {
      "optionId": "prize-slot-1",
      "label": "Prize Card 1",
      "payload": {
        "prizeIndex": 0
      }
    }
  ]
}
```

## **WinnerState**

```json
{
  "winnerPlayerId": "player-1",
  "loserPlayerId": "player-2",
  "reason": "PRIZE_CARDS_TAKEN",
  "finishedAt": "2026-05-05T19:10:00Z"
}
```

## **Reglas**

* `deck`, `hand`, `prizeCards` y `discardPile` contienen `cardInstanceId`.
* El orden del array importa para `deck`, `hand`, `prizeCards` y `discardPile`.
* El primer elemento de `deck` representa la próxima carta a robar.
* `prizeCards` conserva identidad y orden interno en backend.
* `GameState` debe ser suficiente para reconstruir una partida completa.
* Cada acción válida debe incrementar `stateVersion`.
