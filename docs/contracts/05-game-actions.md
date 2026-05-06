# GameAction Contract v1

## Objetivo

`GameAction` representa una intención enviada por un jugador.

El frontend no decide si una acción es válida. Solo la solicita.

El backend valida y resuelve.

## Formato base

```json
{
  "schemaVersion": 1,
  "actionId": "a-001",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "ATTACH_ENERGY",
  "payload": {}
}
```

## **ActionType**

```
CREATE_GAME
JOIN_GAME
START_GAME

DECLARE_MULLIGAN
CHOOSE_INITIAL_ACTIVE
PLACE_INITIAL_BASIC_ON_BENCH
CONFIRM_SETUP
DRAW_MULLIGAN_BONUS_CARD

DRAW_CARD
PLACE_BASIC_ON_BENCH
ATTACH_ENERGY
EVOLVE_POKEMON
PLAY_TRAINER
RETREAT
USE_ABILITY
DECLARE_ATTACK
END_TURN

CHOOSE_ATTACK_TARGET
CHOOSE_PRIZE_CARD
CHOOSE_NEW_ACTIVE_AFTER_KNOCKOUT

CONCEDE
```

## **CREATE_GAME**

```json
{
  "schemaVersion": 1,
  "actionId": "a-create-001",
  "gameId": null,
  "playerId": "player-1",
  "type": "CREATE_GAME",
  "payload": {
    "deckId": "deck-1"
  }
}
```

## **JOIN_GAME**

```json
{
  "schemaVersion": 1,
  "actionId": "a-join-001",
  "gameId": "game-123",
  "playerId": "player-2",
  "type": "JOIN_GAME",
  "payload": {
    "deckId": "deck-2"
  }
}
```

## **CHOOSE_INITIAL_ACTIVE**

```json
{
  "schemaVersion": 1,
  "actionId": "a-setup-001",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "CHOOSE_INITIAL_ACTIVE",
  "payload": {
    "cardInstanceId": "ci-001"
  }
}
```

## **PLACE_INITIAL_BASIC_ON_BENCH**

```json
{
  "schemaVersion": 1,
  "actionId": "a-setup-002",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "PLACE_INITIAL_BASIC_ON_BENCH",
  "payload": {
    "cardInstanceId": "ci-002",
    "benchSlot": 0
  }
}
```

## **CONFIRM_SETUP**

```json
{
  "schemaVersion": 1,
  "actionId": "a-setup-003",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "CONFIRM_SETUP",
  "payload": {}
}
```

## **PLACE_BASIC_ON_BENCH**

```json
{
  "schemaVersion": 1,
  "actionId": "a-010",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "PLACE_BASIC_ON_BENCH",
  "payload": {
    "cardInstanceId": "ci-010",
    "benchSlot": 1
  }
}
```

## **ATTACH_ENERGY**

```json
{
  "schemaVersion": 1,
  "actionId": "a-011",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "ATTACH_ENERGY",
  "payload": {
    "energyCardInstanceId": "ci-011",
    "targetPokemonId": "p-001"
  }
}
```

## **EVOLVE_POKEMON**

```json
{
  "schemaVersion": 1,
  "actionId": "a-012",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "EVOLVE_POKEMON",
  "payload": {
    "evolutionCardInstanceId": "ci-012",
    "targetPokemonId": "p-001"
  }
}
```

## **PLAY_TRAINER**

```json
{
  "schemaVersion": 1,
  "actionId": "a-013",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "PLAY_TRAINER",
  "payload": {
    "trainerCardInstanceId": "ci-013",
    "targetPokemonId": null,
    "selectedCardInstanceIds": []
  }
}
```

## **RETREAT**

```json
{
  "schemaVersion": 1,
  "actionId": "a-014",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "RETREAT",
  "payload": {
    "activePokemonId": "p-001",
    "newActivePokemonId": "p-002",
    "energyCardInstanceIdsToDiscard": ["ci-energy-001"]
  }
}
```

## **DECLARE_ATTACK**

```json
{
  "schemaVersion": 1,
  "actionId": "a-015",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "DECLARE_ATTACK",
  "payload": {
    "attackerPokemonId": "p-001",
    "attackId": "xy1-1-attack-1",
    "targetPokemonId": "p-101",
    "declaredChoices": {}
  }
}
```

## **CHOOSE_PRIZE_CARD**

```json
{
  "schemaVersion": 1,
  "actionId": "a-016",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "CHOOSE_PRIZE_CARD",
  "payload": {
    "requestId": "req-001",
    "prizeIndexes": [0]
  }
}
```

## **CHOOSE_NEW_ACTIVE_AFTER_KNOCKOUT**

```json
{
  "schemaVersion": 1,
  "actionId": "a-017",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "CHOOSE_NEW_ACTIVE_AFTER_KNOCKOUT",
  "payload": {
    "requestId": "req-002",
    "pokemonId": "p-003"
  }
}
```

## **CONCEDE**

```json
{
  "schemaVersion": 1,
  "actionId": "a-018",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "CONCEDE",
  "payload": {}
}
```
