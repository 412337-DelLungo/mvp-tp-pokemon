# GameActionResult Contract v1

## Objetivo

`GameActionResult` es la respuesta del backend luego de procesar una acción.

Puede ser exitosa o rechazada.

## Resultado exitoso

```json
{
  "schemaVersion": 1,
  "success": true,
  "gameId": "game-123",
  "stateVersion": 10,
  "events": [],
  "views": [
    {
      "playerId": "player-1",
      "view": {}
    },
    {
      "playerId": "player-2",
      "view": {}
    }
  ],
  "error": null
}
```

## **Resultado rechazado**

```json
{
  "schemaVersion": 1,
  "success": false,
  "gameId": "game-123",
  "stateVersion": 9,
  "events": [
    {
      "type": "ACTION_REJECTED"
    }
  ],
  "views": [],
  "error": {
    "code": "ENERGY_ALREADY_ATTACHED_THIS_TURN",
    "message": "Ya uniste una Energía este turno."
  }
}
```

## **Reglas**

* Si `success` es `true`, debe haber nuevo `stateVersion`.
* Si `success` es `false`, el `GameState` no debe mutar.
* Una acción exitosa puede generar uno o varios eventos.
* Una acción rechazada puede generar `ACTION_REJECTED`, pero no modifica el estado de juego.
* `views` puede omitirse si la actualización se envía exclusivamente por WebSocket.
