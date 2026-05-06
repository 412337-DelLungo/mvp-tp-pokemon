# REST API Contract v1

## Objetivo

Definir endpoints mínimos entre frontend y backend.

REST se usa para:

- crear partidas,
- unirse a partidas,
- consultar vista,
- enviar acciones,
- consultar logs,
- manejar mazos.

WebSocket se usa para sincronización en tiempo real.

## Games

```http
POST /api/games
GET  /api/games/{gameId}/view
POST /api/games/{gameId}/join
POST /api/games/{gameId}/actions
GET  /api/games/{gameId}/events
```

## **POST /api/games**

Request:

```json
{
  "schemaVersion": 1,
  "playerId": "player-1",
  "deckId": "deck-1"
}
```

Response:

```json
{
  "schemaVersion": 1,
  "gameId": "game-123",
  "status": "WAITING",
  "playerId": "player-1"
}
```

## **POST /api/games/{gameId}/join**

Request:

```json
{
  "schemaVersion": 1,
  "playerId": "player-2",
  "deckId": "deck-2"
}
```

Response:

```json
{
  "schemaVersion": 1,
  "gameId": "game-123",
  "status": "SETUP"
}
```

## **GET /api/games/{gameId}/view**

Query params:

```
playerId=player-1
```

Response:

```json
{
  "schemaVersion": 1,
  "view": {}
}
```

## **POST /api/games/{gameId}/actions**

Request:

```json
{
  "schemaVersion": 1,
  "actionId": "a-001",
  "gameId": "game-123",
  "playerId": "player-1",
  "type": "ATTACH_ENERGY",
  "payload": {
    "energyCardInstanceId": "ci-001",
    "targetPokemonId": "p-001"
  }
}
```

Response:

```json
{
  "schemaVersion": 1,
  "success": true,
  "gameId": "game-123",
  "stateVersion": 2,
  "events": [],
  "error": null
}
```

## **Decks**

```
GET    /api/decks
POST   /api/decks
GET    /api/decks/{deckId}
PUT    /api/decks/{deckId}
DELETE /api/decks/{deckId}
POST   /api/decks/{deckId}/validate
```

## **Cards**

```
GET  /api/cards
GET  /api/cards/{cardId}
POST /api/cards/cache/sync
```

## **Reglas**

* Toda acción de juego entra por `/actions`.
* Ningún endpoint debe modificar el estado de partida fuera del Game Engine.
* `/view` siempre devuelve `GameView`, nunca `GameState`.
* Los endpoints de cartas leen del caché local.
