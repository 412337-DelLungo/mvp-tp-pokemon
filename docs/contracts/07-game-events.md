# GameEvent Contract v1

## Objetivo

`GameEvent` representa algo que ocurrió en la partida.

Sirve para:

- log de acciones,
- auditoría,
- reconexión,
- WebSocket,
- feedback visual,
- persistencia histórica.

## Formato base

```json
{
  "schemaVersion": 1,
  "eventId": "ev-001",
  "gameId": "game-123",
  "stateVersion": 10,
  "turnNumber": 3,
  "type": "ENERGY_ATTACHED",
  "playerId": "player-1",
  "visibility": "PUBLIC",
  "createdAt": "2026-05-05T19:10:00Z",
  "payload": {}
}
```

## **EventType**

```
GAME_CREATED
PLAYER_JOINED
GAME_SETUP_STARTED
DECK_SHUFFLED
INITIAL_HAND_DRAWN
MULLIGAN_DECLARATED
MULLIGAN_BONUS_DRAWN
INITIAL_ACTIVE_CHOSEN
INITIAL_BENCH_CHOSEN
PRIZE_CARDS_SET
GAME_STARTED

TURN_STARTED
CARD_DRAWN
CARD_MOVED
BASIC_PLACED_ON_BENCH
ENERGY_ATTACHED
POKEMON_EVOLVED
TRAINER_PLAYED
STADIUM_REPLACED
POKEMON_TOOL_ATTACHED
POKEMON_RETREATED
ABILITY_USED

ATTACK_DECLARED
ATTACK_FAILED
COIN_FLIPPED
DAMAGE_CALCULATED
DAMAGE_APPLIED
SPECIAL_CONDITION_APPLIED
SPECIAL_CONDITION_REMOVED

BETWEEN_TURNS_STARTED
BETWEEN_TURNS_FINISHED

POKEMON_KNOCKED_OUT
CARD_DISCARDED
PRIZE_CARD_TAKEN
NEW_ACTIVE_CHOSEN

PENDING_REQUEST_CREATED
PENDING_REQUEST_RESOLVED

GAME_FINISHED
SUDDEN_DEATH_STARTED
ACTION_REJECTED
PLAYER_DISCONNECTED
PLAYER_RECONNECTED
```

## **Ejemplo: ENERGY_ATTACHED**

```json
{
  "schemaVersion": 1,
  "eventId": "ev-010",
  "gameId": "game-123",
  "stateVersion": 10,
  "turnNumber": 3,
  "type": "ENERGY_ATTACHED",
  "playerId": "player-1",
  "visibility": "PUBLIC",
  "createdAt": "2026-05-05T19:10:00Z",
  "payload": {
    "energyCardInstanceId": "ci-010",
    "targetPokemonId": "p-001"
  }
}
```

## **Ejemplo: DAMAGE_APPLIED**

```json
{
  "schemaVersion": 1,
  "eventId": "ev-011",
  "gameId": "game-123",
  "stateVersion": 11,
  "turnNumber": 3,
  "type": "DAMAGE_APPLIED",
  "playerId": "player-1",
  "visibility": "PUBLIC",
  "createdAt": "2026-05-05T19:10:10Z",
  "payload": {
    "sourcePokemonId": "p-001",
    "targetPokemonId": "p-101",
    "damagePoints": 60,
    "damageCountersAdded": 6,
    "totalDamageCounters": 8
  }
}
```

## **Ejemplo: POKEMON_KNOCKED_OUT**

```json
{
  "schemaVersion": 1,
  "eventId": "ev-012",
  "gameId": "game-123",
  "stateVersion": 12,
  "turnNumber": 3,
  "type": "POKEMON_KNOCKED_OUT",
  "playerId": "player-1",
  "visibility": "PUBLIC",
  "createdAt": "2026-05-05T19:10:15Z",
  "payload": {
    "knockedOutPokemonId": "p-101",
    "ownerPlayerId": "player-2",
    "prizeCardsToTake": 1
  }
}
```

## **Ejemplo: ACTION_REJECTED**

```json
{
  "schemaVersion": 1,
  "eventId": "ev-013",
  "gameId": "game-123",
  "stateVersion": 12,
  "turnNumber": 3,
  "type": "ACTION_REJECTED",
  "playerId": "player-1",
  "visibility": "PRIVATE_TO_PLAYER",
  "createdAt": "2026-05-05T19:10:20Z",
  "payload": {
    "actionId": "a-020",
    "errorCode": "NOT_ENOUGH_ENERGY"
  }
}
```
