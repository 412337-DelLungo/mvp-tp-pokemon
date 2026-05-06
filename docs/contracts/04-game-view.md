# GameView Contract v1

## Objetivo

`GameView` representa la vista segura que recibe un jugador.

A diferencia de `GameState`, este contrato sí puede enviarse al frontend.

Cada jugador recibe una `GameView` distinta.

## GameView

```json
{
  "schemaVersion": 1,
  "gameId": "game-123",
  "stateVersion": 1,
  "viewerPlayerId": "player-1",
  "status": "ACTIVE",
  "turn": {},
  "self": {},
  "opponent": {},
  "stadiumCard": null,
  "pendingRequest": null,
  "availableActions": [],
  "lastEvents": []
}
```

## **PublicTurnView**

```json
{
  "turnNumber": 3,
  "currentPlayerId": "player-1",
  "phase": "MAIN",
  "isViewerTurn": true,
  "flags": {
    "hasAttachedEnergyThisTurn": false,
    "hasRetreatedThisTurn": false,
    "hasPlayedSupporterThisTurn": false,
    "hasPlayedStadiumThisTurn": false,
    "hasAttackedThisTurn": false
  }
}
```

## **SelfPlayerView**

```json
{
  "playerId": "player-1",
  "displayName": "Player 1",
  "hand": [],
  "deckCount": 42,
  "prizeCount": 6,
  "discardPile": [],
  "lostZone": [],
  "board": {}
}
```

## **OpponentPlayerView**

```json
{
  "playerId": "player-2",
  "displayName": "Player 2",
  "handCount": 5,
  "deckCount": 43,
  "prizeCount": 6,
  "discardPile": [],
  "lostZone": [],
  "board": {}
}
```

## **CardView**

```json
{
  "cardInstanceId": "ci-001",
  "cardId": "xy1-1",
  "name": "Venusaur-EX",
  "supertype": "POKEMON",
  "subtypes": ["BASIC", "EX"],
  "imageUrl": "https://...",
  "isFaceDown": false
}
```

## **HiddenCardView**

```json
{
  "cardInstanceId": null,
  "cardId": null,
  "name": null,
  "supertype": null,
  "subtypes": [],
  "imageUrl": null,
  "isFaceDown": true
}
```

## **PokemonInPlayView**

```json
{
  "pokemonId": "p-001",
  "ownerPlayerId": "player-1",
  "cardInstanceId": "ci-100",
  "card": {},
  "zone": "ACTIVE",
  "benchSlot": null,
  "damageCounters": 0,
  "currentHp": 180,
  "maxHp": 180,
  "attachedEnergies": [],
  "attachedTool": null,
  "evolutionCards": [],
  "specialConditions": [],
  "canBeSelected": false,
  "validActions": []
}
```

## **AvailableActionView**

```json
{
  "type": "ATTACH_ENERGY",
  "label": "Unir Energía",
  "enabled": true,
  "reasonDisabled": null
}
```

## **Reglas**

* `self.hand` muestra cartas completas.
* `opponent.hand` nunca existe.
* `opponent.handCount` sí existe.
* `deckCount` se muestra, pero no las cartas del mazo.
* `prizeCount` se muestra, pero no la identidad de cartas de Premio.
* La pila de descarte es pública.
* El estado de Pokémon en juego es público.
* Las energías y herramientas unidas son públicas.
* El daño y las condiciones especiales son públicos.
