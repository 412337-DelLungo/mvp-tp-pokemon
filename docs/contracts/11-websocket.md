# WebSocket Contract v1

## Objetivo

Sincronizar estado y eventos en tiempo real entre servidor y clientes.

El frontend puede enviar acciones por REST en la primera versión.
WebSocket se usa para recibir eventos y vistas actualizadas.

## Canales

```
/topic/games/{gameId}/public
/user/queue/games/{gameId}/private
```

## **Mensaje público: GAME_EVENTS**

```json
{
  "schemaVersion": 1,
  "messageType": "GAME_EVENTS",
  "gameId": "game-123",
  "stateVersion": 10,
  "events": []
}
```

## **Mensaje privado: GAME_VIEW_UPDATED**

```json
{
  "schemaVersion": 1,
  "messageType": "GAME_VIEW_UPDATED",
  "gameId": "game-123",
  "viewerPlayerId": "player-1",
  "stateVersion": 10,
  "view": {}
}
```

## **Mensaje privado: ACTION_REJECTED**

```json
{
  "schemaVersion": 1,
  "messageType": "ACTION_REJECTED",
  "gameId": "game-123",
  "viewerPlayerId": "player-1",
  "stateVersion": 10,
  "error": {
    "code": "INVALID_PHASE",
    "message": "No puedes realizar esta acción en la fase actual."
  }
}
```

## **Mensaje público: PLAYER_CONNECTION_CHANGED**

```json
{
  "schemaVersion": 1,
  "messageType": "PLAYER_CONNECTION_CHANGED",
  "gameId": "game-123",
  "playerId": "player-2",
  "isConnected": false
}
```

## **Reglas**

* Los eventos públicos no deben incluir información oculta.
* Las vistas privadas se envían por canal privado.
* Cada jugador recibe su propia `GameView`.
* Ante reconexión, el backend debe enviar la última `GameView` del jugador.
* El cliente no debe reconstruir reglas; solo renderiza lo recibido.
