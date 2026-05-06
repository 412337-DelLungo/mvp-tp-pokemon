# **Carpeta a crear**

docs/  
 contracts/  
   00-contract-rules.md  
   01-enums.md  
   02-card-definition.md  
   03-game-state.md  
   04-game-view.md  
   05-game-actions.md  
   06-game-action-result.md  
   07-game-events.md  
   08-game-errors.md  
   09-deck-contracts.md  
   10-api-rest.md  
   11-websocket.md  
   12-visibility-rules.md  
---

# **00-contract-rules.md**

\# Contract Rules v1

\#\# Objetivo

Estos contratos definen el lenguaje común entre backend, frontend, Game Engine, WebSockets, persistencia y tests.

No son implementación. Son acuerdos de datos y comportamiento.

\#\# Reglas generales

1\. Todo contrato debe incluir \`schemaVersion\`.  
2\. Todo estado de partida debe incluir \`stateVersion\`.  
3\. El backend es la única fuente de verdad.  
4\. El frontend nunca modifica el estado directamente.  
5\. El frontend solo envía \`GameAction\`.  
6\. El backend responde con \`GameActionResult\`.  
7\. Todo cambio relevante genera uno o más \`GameEvent\`.  
8\. El estado interno completo se representa con \`GameState\`.  
9\. El frontend recibe solo \`GameView\`, nunca \`GameState\`.  
10\. La información oculta no debe enviarse al cliente incorrecto.

\#\# Extensibilidad

Está permitido:

\- Agregar campos opcionales.  
\- Agregar nuevos \`ActionType\`.  
\- Agregar nuevos \`EventType\`.  
\- Agregar nuevos \`GameErrorCode\`.  
\- Agregar nuevos valores de enums si son necesarios.

No está permitido sin crear \`schemaVersion: 2\`:

\- Cambiar el significado de un campo existente.  
\- Cambiar el tipo de dato de un campo existente.  
\- Reutilizar un código de error con otro significado.  
\- Enviar datos ocultos dentro de \`GameView\`.

\#\# Convenciones de nombres

\- Los ids son strings.  
\- Los enums usan \`UPPER\_SNAKE\_CASE\`.  
\- Los campos usan \`camelCase\`.  
\- El daño se modela como contadores de daño con \`damageCounters\`.  
\- Si se necesita daño en puntos, se usa \`damagePoints\`.  
\- 1 contador de daño equivale a 10 puntos de daño.

\#\# Versionado

Todos los contratos de esta carpeta pertenecen a:

\`\`\`json  
{  
 "schemaVersion": 1  
}

\---

\# 01-enums.md

\`\`\`md  
\# Enums Contract v1

\#\# GameStatus

Estados generales de una partida.

\`\`\`text  
WAITING  
SETUP  
ACTIVE  
FINISHED

## **TurnPhase**

Fases del turno o puntos de espera del motor.

DRAW  
MAIN  
ATTACK  
BETWEEN\_TURNS  
AWAITING\_PLAYER\_CHOICE  
FINISHED

`AWAITING_PLAYER_CHOICE` se usa cuando el motor necesita una decisión del jugador, por ejemplo:

* elegir Pokémon Activo inicial,  
* elegir Pokémon de reemplazo después de un knockout,  
* elegir carta de Premio,  
* elegir objetivo de ataque,  
* resolver setup o mulligan.

## **Zone**

Zonas posibles de una carta durante la partida.

DECK  
HAND  
ACTIVE  
BENCH  
PRIZE  
DISCARD  
STADIUM  
LOST\_ZONE

## **CardSupertype**

Tipos principales de carta.

POKEMON  
ENERGY  
TRAINER

## **PokemonStage**

BASIC  
STAGE\_1  
STAGE\_2  
MEGA  
RESTORED  
UNKNOWN

Para el MVP se soportan principalmente:

BASIC  
STAGE\_1  
STAGE\_2

## **TrainerSubtype**

ITEM  
SUPPORTER  
STADIUM  
POKEMON\_TOOL  
ACE\_SPEC  
FOSSIL  
UNKNOWN

## **EnergyType**

GRASS  
FIRE  
WATER  
LIGHTNING  
PSYCHIC  
FIGHTING  
DARKNESS  
METAL  
FAIRY  
DRAGON  
COLORLESS

## **SpecialCondition**

ASLEEP  
BURNED  
CONFUSED  
PARALYZED  
POISONED

## **EventVisibility**

PUBLIC  
PRIVATE\_TO\_PLAYER  
PRIVATE\_TO\_OPPONENT  
SYSTEM\_ONLY

## **GameEndReason**

PRIZE\_CARDS\_TAKEN  
OPPONENT\_HAS\_NO\_POKEMON  
DECK\_EMPTY\_ON\_DRAW  
CONCEDE  
SUDDEN\_DEATH\_RESOLVED

## **PendingRequestType**

CHOOSE\_INITIAL\_ACTIVE  
CHOOSE\_INITIAL\_BENCH  
CONFIRM\_SETUP  
CHOOSE\_ATTACK\_TARGET  
CHOOSE\_PRIZE\_CARD  
CHOOSE\_NEW\_ACTIVE\_AFTER\_KNOCKOUT  
CHOOSE\_CARDS\_FROM\_DECK  
CHOOSE\_CARDS\_TO\_DISCARD  
RESPOND\_TO\_MULLIGAN\_DRAW

\---

\# 02-card-definition.md

\`\`\`md  
\# CardDefinition Contract v1

\#\# Objetivo

\`CardDefinition\` representa los datos oficiales/cacheados de una carta.

Estos datos vienen de la API externa \`pokemontcg.io\` y se guardan en base de datos local. Durante una partida, el motor debe leer cartas desde el caché local, no desde la API externa.

\#\# CardDefinition

\`\`\`json  
{  
 "schemaVersion": 1,  
 "cardId": "xy1-1",  
 "setId": "xy1",  
 "number": "1",  
 "name": "Venusaur-EX",  
 "normalizedName": "VENUSAUR-EX",  
 "supertype": "POKEMON",  
 "subtypes": \["BASIC", "EX"\],  
 "pokemonStage": "BASIC",  
 "trainerSubtype": null,  
 "energyTypes": \[\],  
 "hp": 180,  
 "types": \["GRASS"\],  
 "evolvesFrom": null,  
 "evolvesTo": \[\],  
 "attacks": \[\],  
 "abilities": \[\],  
 "weaknesses": \[\],  
 "resistances": \[\],  
 "retreatCost": \["COLORLESS", "COLORLESS", "COLORLESS"\],  
 "convertedRetreatCost": 3,  
 "rules": \[\],  
 "legalities": {},  
 "images": {  
   "small": "https://...",  
   "large": "https://..."  
 },  
 "rawText": null  
}

## **AttackDefinition**

{  
 "attackId": "xy1-1-attack-1",  
 "name": "Poison Powder",  
 "cost": \["GRASS", "COLORLESS", "COLORLESS"\],  
 "convertedEnergyCost": 3,  
 "damageText": "60",  
 "baseDamagePoints": 60,  
 "damageModifierSymbol": null,  
 "text": "Your opponent's Active Pokémon is now Poisoned.",  
 "implementedEffectKey": "APPLY\_POISON",  
 "requiresTarget": true,  
 "targetRule": "OPPONENT\_ACTIVE"  
}

## **AbilityDefinition**

{  
 "abilityId": "xy1-1-ability-1",  
 "name": "Verdant Wind",  
 "type": "ABILITY",  
 "text": "Each of your Pokémon that has any Grass Energy attached to it can't be affected by any Special Conditions.",  
 "implementedEffectKey": null  
}

## **WeaknessDefinition**

{  
 "type": "FIRE",  
 "value": "x2"  
}

## **ResistanceDefinition**

{  
 "type": "WATER",  
 "value": "-20"  
}

## **Reglas**

* `cardId` identifica la carta oficial.  
* `cardInstanceId` identifica una copia concreta dentro de una partida.  
* Puede haber muchas instancias con el mismo `cardId`.  
* El texto libre de ataques y habilidades se conserva, pero no se interpreta automáticamente.  
* Los efectos implementados se resuelven mediante `implementedEffectKey`.  
* Si un efecto todavía no está implementado, `implementedEffectKey` puede ser `null`.

\---

\# 03-game-state.md

\`\`\`md  
\# GameState Contract v1

\#\# Objetivo

\`GameState\` representa el estado interno completo de una partida.

Solo vive en backend.

No debe enviarse directamente al frontend porque contiene información oculta:

\- mano de ambos jugadores,  
\- orden de los mazos,  
\- cartas de Premio,  
\- requests internos,  
\- datos completos para reconstrucción.

\#\# GameState

\`\`\`json  
{  
 "schemaVersion": 1,  
 "gameId": "game-123",  
 "stateVersion": 1,  
 "status": "ACTIVE",  
 "createdAt": "2026-05-05T19:00:00Z",  
 "updatedAt": "2026-05-05T19:02:00Z",  
 "players": \[\],  
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

## **PlayerState**

{  
 "playerId": "player-1",  
 "displayName": "Player 1",  
 "deckId": "deck-1",  
 "deck": \[\],  
 "hand": \[\],  
 "prizeCards": \[\],  
 "discardPile": \[\],  
 "lostZone": \[\],  
 "board": {},  
 "setup": {},  
 "connection": {}  
}

## **CardInstance**

{  
 "cardInstanceId": "ci-001",  
 "cardId": "xy1-1",  
 "ownerPlayerId": "player-1",  
 "currentZone": "HAND",  
 "isFaceDown": false  
}

## **BoardState**

{  
 "active": null,  
 "bench": \[\]  
}

## **PokemonInPlay**

{  
 "pokemonId": "p-001",  
 "ownerPlayerId": "player-1",  
 "cardInstanceId": "ci-100",  
 "cardId": "xy1-1",  
 "zone": "ACTIVE",  
 "benchSlot": null,  
 "damageCounters": 0,  
 "attachedEnergyCardInstanceIds": \[\],  
 "attachedToolCardInstanceId": null,  
 "evolutionCardInstanceIds": \[\],  
 "specialConditions": \[\],  
 "enteredPlayTurn": 1,  
 "lastEvolvedTurn": null,  
 "evolvedThisTurn": false,  
 "temporaryEffects": \[\],  
 "abilityStates": \[\]  
}

## **SpecialConditionState**

{  
 "condition": "POISONED",  
 "appliedTurn": 3,  
 "sourceCardInstanceId": "ci-555",  
 "sourceAttackId": "xy1-1-attack-1"  
}

## **TurnState**

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

## **SetupState**

{  
 "hasConfirmedInitialActive": true,  
 "hasConfirmedInitialBench": true,  
 "mulliganCount": 0,  
 "canDrawMulliganBonusCards": 0,  
 "setupComplete": true  
}

## **ConnectionState**

{  
 "isConnected": true,  
 "lastSeenAt": "2026-05-05T19:02:00Z"  
}

## **PendingRequest**

{  
 "requestId": "req-001",  
 "type": "CHOOSE\_PRIZE\_CARD",  
 "playerId": "player-1",  
 "sourceActionId": "a-010",  
 "required": true,  
 "minChoices": 1,  
 "maxChoices": 1,  
 "validOptions": \[  
   {  
     "optionId": "prize-slot-1",  
     "label": "Prize Card 1",  
     "payload": {  
       "prizeIndex": 0  
     }  
   }  
 \]  
}

## **WinnerState**

{  
 "winnerPlayerId": "player-1",  
 "loserPlayerId": "player-2",  
 "reason": "PRIZE\_CARDS\_TAKEN",  
 "finishedAt": "2026-05-05T19:10:00Z"  
}

## **Reglas**

* `deck`, `hand`, `prizeCards` y `discardPile` contienen `cardInstanceId`.  
* El orden del array importa para `deck`, `hand`, `prizeCards` y `discardPile`.  
* El primer elemento de `deck` representa la próxima carta a robar.  
* `prizeCards` conserva identidad y orden interno en backend.  
* `GameState` debe ser suficiente para reconstruir una partida completa.  
* Cada acción válida debe incrementar `stateVersion`.

\---

\# 04-game-view.md

\`\`\`md  
\# GameView Contract v1

\#\# Objetivo

\`GameView\` representa la vista segura que recibe un jugador.

A diferencia de \`GameState\`, este contrato sí puede enviarse al frontend.

Cada jugador recibe una \`GameView\` distinta.

\#\# GameView

\`\`\`json  
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
 "availableActions": \[\],  
 "lastEvents": \[\]  
}

## **PublicTurnView**

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

## **SelfPlayerView**

{  
 "playerId": "player-1",  
 "displayName": "Player 1",  
 "hand": \[\],  
 "deckCount": 42,  
 "prizeCount": 6,  
 "discardPile": \[\],  
 "lostZone": \[\],  
 "board": {}  
}

## **OpponentPlayerView**

{  
 "playerId": "player-2",  
 "displayName": "Player 2",  
 "handCount": 5,  
 "deckCount": 43,  
 "prizeCount": 6,  
 "discardPile": \[\],  
 "lostZone": \[\],  
 "board": {}  
}

## **CardView**

{  
 "cardInstanceId": "ci-001",  
 "cardId": "xy1-1",  
 "name": "Venusaur-EX",  
 "supertype": "POKEMON",  
 "subtypes": \["BASIC", "EX"\],  
 "imageUrl": "https://...",  
 "isFaceDown": false  
}

## **HiddenCardView**

{  
 "cardInstanceId": null,  
 "cardId": null,  
 "name": null,  
 "supertype": null,  
 "subtypes": \[\],  
 "imageUrl": null,  
 "isFaceDown": true  
}

## **PokemonInPlayView**

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
 "attachedEnergies": \[\],  
 "attachedTool": null,  
 "evolutionCards": \[\],  
 "specialConditions": \[\],  
 "canBeSelected": false,  
 "validActions": \[\]  
}

## **AvailableActionView**

{  
 "type": "ATTACH\_ENERGY",  
 "label": "Unir Energía",  
 "enabled": true,  
 "reasonDisabled": null  
}

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

\---

\# 05-game-actions.md

\`\`\`md  
\# GameAction Contract v1

\#\# Objetivo

\`GameAction\` representa una intención enviada por un jugador.

El frontend no decide si una acción es válida. Solo la solicita.

El backend valida y resuelve.

\#\# Formato base

\`\`\`json  
{  
 "schemaVersion": 1,  
 "actionId": "a-001",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "ATTACH\_ENERGY",  
 "payload": {}  
}

## **ActionType**

CREATE\_GAME  
JOIN\_GAME  
START\_GAME

DECLARE\_MULLIGAN  
CHOOSE\_INITIAL\_ACTIVE  
PLACE\_INITIAL\_BASIC\_ON\_BENCH  
CONFIRM\_SETUP  
DRAW\_MULLIGAN\_BONUS\_CARD

DRAW\_CARD  
PLACE\_BASIC\_ON\_BENCH  
ATTACH\_ENERGY  
EVOLVE\_POKEMON  
PLAY\_TRAINER  
RETREAT  
USE\_ABILITY  
DECLARE\_ATTACK  
END\_TURN

CHOOSE\_ATTACK\_TARGET  
CHOOSE\_PRIZE\_CARD  
CHOOSE\_NEW\_ACTIVE\_AFTER\_KNOCKOUT

CONCEDE

## **CREATE\_GAME**

{  
 "schemaVersion": 1,  
 "actionId": "a-create-001",  
 "gameId": null,  
 "playerId": "player-1",  
 "type": "CREATE\_GAME",  
 "payload": {  
   "deckId": "deck-1"  
 }  
}

## **JOIN\_GAME**

{  
 "schemaVersion": 1,  
 "actionId": "a-join-001",  
 "gameId": "game-123",  
 "playerId": "player-2",  
 "type": "JOIN\_GAME",  
 "payload": {  
   "deckId": "deck-2"  
 }  
}

## **CHOOSE\_INITIAL\_ACTIVE**

{  
 "schemaVersion": 1,  
 "actionId": "a-setup-001",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "CHOOSE\_INITIAL\_ACTIVE",  
 "payload": {  
   "cardInstanceId": "ci-001"  
 }  
}

## **PLACE\_INITIAL\_BASIC\_ON\_BENCH**

{  
 "schemaVersion": 1,  
 "actionId": "a-setup-002",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "PLACE\_INITIAL\_BASIC\_ON\_BENCH",  
 "payload": {  
   "cardInstanceId": "ci-002",  
   "benchSlot": 0  
 }  
}

## **CONFIRM\_SETUP**

{  
 "schemaVersion": 1,  
 "actionId": "a-setup-003",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "CONFIRM\_SETUP",  
 "payload": {}  
}

## **PLACE\_BASIC\_ON\_BENCH**

{  
 "schemaVersion": 1,  
 "actionId": "a-010",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "PLACE\_BASIC\_ON\_BENCH",  
 "payload": {  
   "cardInstanceId": "ci-010",  
   "benchSlot": 1  
 }  
}

## **ATTACH\_ENERGY**

{  
 "schemaVersion": 1,  
 "actionId": "a-011",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "ATTACH\_ENERGY",  
 "payload": {  
   "energyCardInstanceId": "ci-011",  
   "targetPokemonId": "p-001"  
 }  
}

## **EVOLVE\_POKEMON**

{  
 "schemaVersion": 1,  
 "actionId": "a-012",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "EVOLVE\_POKEMON",  
 "payload": {  
   "evolutionCardInstanceId": "ci-012",  
   "targetPokemonId": "p-001"  
 }  
}

## **PLAY\_TRAINER**

{  
 "schemaVersion": 1,  
 "actionId": "a-013",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "PLAY\_TRAINER",  
 "payload": {  
   "trainerCardInstanceId": "ci-013",  
   "targetPokemonId": null,  
   "selectedCardInstanceIds": \[\]  
 }  
}

## **RETREAT**

{  
 "schemaVersion": 1,  
 "actionId": "a-014",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "RETREAT",  
 "payload": {  
   "activePokemonId": "p-001",  
   "newActivePokemonId": "p-002",  
   "energyCardInstanceIdsToDiscard": \["ci-energy-001"\]  
 }  
}

## **DECLARE\_ATTACK**

{  
 "schemaVersion": 1,  
 "actionId": "a-015",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "DECLARE\_ATTACK",  
 "payload": {  
   "attackerPokemonId": "p-001",  
   "attackId": "xy1-1-attack-1",  
   "targetPokemonId": "p-101",  
   "declaredChoices": {}  
 }  
}

## **CHOOSE\_PRIZE\_CARD**

{  
 "schemaVersion": 1,  
 "actionId": "a-016",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "CHOOSE\_PRIZE\_CARD",  
 "payload": {  
   "requestId": "req-001",  
   "prizeIndexes": \[0\]  
 }  
}

## **CHOOSE\_NEW\_ACTIVE\_AFTER\_KNOCKOUT**

{  
 "schemaVersion": 1,  
 "actionId": "a-017",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "CHOOSE\_NEW\_ACTIVE\_AFTER\_KNOCKOUT",  
 "payload": {  
   "requestId": "req-002",  
   "pokemonId": "p-003"  
 }  
}

## **CONCEDE**

{  
 "schemaVersion": 1,  
 "actionId": "a-018",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "CONCEDE",  
 "payload": {}  
}

\---

\# 06-game-action-result.md

\`\`\`md  
\# GameActionResult Contract v1

\#\# Objetivo

\`GameActionResult\` es la respuesta del backend luego de procesar una acción.

Puede ser exitosa o rechazada.

\#\# Resultado exitoso

\`\`\`json  
{  
 "schemaVersion": 1,  
 "success": true,  
 "gameId": "game-123",  
 "stateVersion": 10,  
 "events": \[\],  
 "views": \[  
   {  
     "playerId": "player-1",  
     "view": {}  
   },  
   {  
     "playerId": "player-2",  
     "view": {}  
   }  
 \],  
 "error": null  
}

## **Resultado rechazado**

{  
 "schemaVersion": 1,  
 "success": false,  
 "gameId": "game-123",  
 "stateVersion": 9,  
 "events": \[  
   {  
     "type": "ACTION\_REJECTED"  
   }  
 \],  
 "views": \[\],  
 "error": {  
   "code": "ENERGY\_ALREADY\_ATTACHED\_THIS\_TURN",  
   "message": "Ya uniste una Energía este turno."  
 }  
}

## **Reglas**

* Si `success` es `true`, debe haber nuevo `stateVersion`.  
* Si `success` es `false`, el `GameState` no debe mutar.  
* Una acción exitosa puede generar uno o varios eventos.  
* Una acción rechazada puede generar `ACTION_REJECTED`, pero no modifica el estado de juego.  
* `views` puede omitirse si la actualización se envía exclusivamente por WebSocket.

\---

\# 07-game-events.md

\`\`\`md  
\# GameEvent Contract v1

\#\# Objetivo

\`GameEvent\` representa algo que ocurrió en la partida.

Sirve para:

\- log de acciones,  
\- auditoría,  
\- reconexión,  
\- WebSocket,  
\- feedback visual,  
\- persistencia histórica.

\#\# Formato base

\`\`\`json  
{  
 "schemaVersion": 1,  
 "eventId": "ev-001",  
 "gameId": "game-123",  
 "stateVersion": 10,  
 "turnNumber": 3,  
 "type": "ENERGY\_ATTACHED",  
 "playerId": "player-1",  
 "visibility": "PUBLIC",  
 "createdAt": "2026-05-05T19:10:00Z",  
 "payload": {}  
}

## **EventType**

GAME\_CREATED  
PLAYER\_JOINED  
GAME\_SETUP\_STARTED  
DECK\_SHUFFLED  
INITIAL\_HAND\_DRAWN  
MULLIGAN\_DECLARED  
MULLIGAN\_BONUS\_DRAWN  
INITIAL\_ACTIVE\_CHOSEN  
INITIAL\_BENCH\_CHOSEN  
PRIZE\_CARDS\_SET  
GAME\_STARTED

TURN\_STARTED  
CARD\_DRAWN  
CARD\_MOVED  
BASIC\_PLACED\_ON\_BENCH  
ENERGY\_ATTACHED  
POKEMON\_EVOLVED  
TRAINER\_PLAYED  
STADIUM\_REPLACED  
POKEMON\_TOOL\_ATTACHED  
POKEMON\_RETREATED  
ABILITY\_USED

ATTACK\_DECLARED  
ATTACK\_FAILED  
COIN\_FLIPPED  
DAMAGE\_CALCULATED  
DAMAGE\_APPLIED  
SPECIAL\_CONDITION\_APPLIED  
SPECIAL\_CONDITION\_REMOVED

BETWEEN\_TURNS\_STARTED  
BETWEEN\_TURNS\_FINISHED

POKEMON\_KNOCKED\_OUT  
CARD\_DISCARDED  
PRIZE\_CARD\_TAKEN  
NEW\_ACTIVE\_CHOSEN

PENDING\_REQUEST\_CREATED  
PENDING\_REQUEST\_RESOLVED

GAME\_FINISHED  
SUDDEN\_DEATH\_STARTED  
ACTION\_REJECTED  
PLAYER\_DISCONNECTED  
PLAYER\_RECONNECTED

## **Ejemplo: ENERGY\_ATTACHED**

{  
 "schemaVersion": 1,  
 "eventId": "ev-010",  
 "gameId": "game-123",  
 "stateVersion": 10,  
 "turnNumber": 3,  
 "type": "ENERGY\_ATTACHED",  
 "playerId": "player-1",  
 "visibility": "PUBLIC",  
 "createdAt": "2026-05-05T19:10:00Z",  
 "payload": {  
   "energyCardInstanceId": "ci-010",  
   "targetPokemonId": "p-001"  
 }  
}

## **Ejemplo: DAMAGE\_APPLIED**

{  
 "schemaVersion": 1,  
 "eventId": "ev-011",  
 "gameId": "game-123",  
 "stateVersion": 11,  
 "turnNumber": 3,  
 "type": "DAMAGE\_APPLIED",  
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

## **Ejemplo: POKEMON\_KNOCKED\_OUT**

{  
 "schemaVersion": 1,  
 "eventId": "ev-012",  
 "gameId": "game-123",  
 "stateVersion": 12,  
 "turnNumber": 3,  
 "type": "POKEMON\_KNOCKED\_OUT",  
 "playerId": "player-1",  
 "visibility": "PUBLIC",  
 "createdAt": "2026-05-05T19:10:15Z",  
 "payload": {  
   "knockedOutPokemonId": "p-101",  
   "ownerPlayerId": "player-2",  
   "prizeCardsToTake": 1  
 }  
}

## **Ejemplo: ACTION\_REJECTED**

{  
 "schemaVersion": 1,  
 "eventId": "ev-013",  
 "gameId": "game-123",  
 "stateVersion": 12,  
 "turnNumber": 3,  
 "type": "ACTION\_REJECTED",  
 "playerId": "player-1",  
 "visibility": "PRIVATE\_TO\_PLAYER",  
 "createdAt": "2026-05-05T19:10:20Z",  
 "payload": {  
   "actionId": "a-020",  
   "errorCode": "NOT\_ENOUGH\_ENERGY"  
 }  
}

\---

\# 08-game-errors.md

\`\`\`md  
\# GameError Contract v1

\#\# Objetivo

\`GameError\` representa una acción inválida o un problema de procesamiento.

Los errores deben ser claros para el frontend y útiles para el jugador.

\#\# Formato

\`\`\`json  
{  
 "code": "NOT\_ENOUGH\_ENERGY",  
 "message": "No puedes atacar: faltan Energías para usar este ataque.",  
 "details": {}  
}

## **GameErrorCode**

GAME\_NOT\_FOUND  
PLAYER\_NOT\_FOUND  
PLAYER\_NOT\_IN\_GAME  
GAME\_FULL  
GAME\_NOT\_ACTIVE  
GAME\_ALREADY\_STARTED  
GAME\_ALREADY\_FINISHED

NOT\_YOUR\_TURN  
INVALID\_PHASE  
INVALID\_ACTION  
INVALID\_TARGET  
PENDING\_REQUEST\_REQUIRED  
INVALID\_PENDING\_REQUEST  
PENDING\_REQUEST\_NOT\_FOR\_PLAYER

CARD\_NOT\_FOUND  
CARD\_NOT\_IN\_HAND  
CARD\_NOT\_IN\_DECK  
CARD\_NOT\_IN\_PLAY  
CARD\_NOT\_OWNED\_BY\_PLAYER  
CARD\_IS\_NOT\_BASIC\_POKEMON  
CARD\_IS\_NOT\_POKEMON  
CARD\_IS\_NOT\_ENERGY  
CARD\_IS\_NOT\_EVOLUTION  
CARD\_IS\_NOT\_TRAINER

BENCH\_FULL  
NO\_ACTIVE\_POKEMON  
TARGET\_NOT\_IN\_PLAY  
TARGET\_IS\_NOT\_YOUR\_POKEMON  
TARGET\_IS\_NOT\_OPPONENT\_POKEMON

ENERGY\_ALREADY\_ATTACHED\_THIS\_TURN  
SUPPORTER\_ALREADY\_PLAYED\_THIS\_TURN  
STADIUM\_ALREADY\_PLAYED\_THIS\_TURN  
RETREAT\_ALREADY\_USED\_THIS\_TURN

NOT\_ENOUGH\_ENERGY  
CANNOT\_ATTACK\_FIRST\_TURN  
CANNOT\_ATTACK\_ASLEEP  
CANNOT\_ATTACK\_PARALYZED  
CANNOT\_RETREAT\_ASLEEP  
CANNOT\_RETREAT\_PARALYZED  
CANNOT\_RETREAT\_NO\_BENCH  
NOT\_ENOUGH\_ENERGY\_TO\_RETREAT

EVOLUTION\_NOT\_ALLOWED\_FIRST\_TURN  
EVOLUTION\_NOT\_ALLOWED\_SAME\_TURN\_PLAYED  
EVOLUTION\_NOT\_ALLOWED\_SAME\_TURN\_EVOLVED  
INVALID\_EVOLUTION\_CHAIN

TRAINER\_EFFECT\_NOT\_IMPLEMENTED  
ATTACK\_EFFECT\_NOT\_IMPLEMENTED  
ABILITY\_EFFECT\_NOT\_IMPLEMENTED

DECK\_EMPTY\_ON\_DRAW  
NO\_PRIZE\_CARDS\_LEFT  
NO\_BENCH\_POKEMON\_TO\_PROMOTE

DECK\_INVALID\_SIZE  
DECK\_TOO\_MANY\_COPIES  
DECK\_MISSING\_BASIC\_POKEMON  
DECK\_TOO\_MANY\_ACE\_SPEC  
CARD\_NOT\_ALLOWED\_IN\_FORMAT

INTERNAL\_ERROR

## **Ejemplo con details**

{  
 "code": "NOT\_ENOUGH\_ENERGY",  
 "message": "No puedes atacar: faltan Energías para usar este ataque.",  
 "details": {  
   "attackId": "xy1-1-attack-1",  
   "requiredEnergy": \["GRASS", "COLORLESS", "COLORLESS"\],  
   "availableEnergy": \["GRASS"\]  
 }  
}

\---

\# 09-deck-contracts.md

\`\`\`md  
\# Deck Contracts v1

\#\# Objetivo

Definir contratos para construcción, validación y uso de mazos.

El Deck Builder trabaja con cartas cacheadas desde \`pokemontcg.io\`, usando el set obligatorio \`xy1\`.

\#\# DeckSummary

\`\`\`json  
{  
 "schemaVersion": 1,  
 "deckId": "deck-1",  
 "ownerPlayerId": "player-1",  
 "name": "XY Grass Deck",  
 "cardCount": 60,  
 "isValid": true,  
 "validationErrors": \[\],  
 "createdAt": "2026-05-05T19:00:00Z",  
 "updatedAt": "2026-05-05T19:00:00Z"  
}

## **DeckDetail**

{  
 "schemaVersion": 1,  
 "deckId": "deck-1",  
 "ownerPlayerId": "player-1",  
 "name": "XY Grass Deck",  
 "cards": \[\],  
 "validation": {}  
}

## **DeckCardEntry**

{  
 "cardId": "xy1-1",  
 "name": "Venusaur-EX",  
 "count": 2,  
 "supertype": "POKEMON",  
 "subtypes": \["BASIC", "EX"\]  
}

## **DeckValidationResult**

{  
 "isValid": true,  
 "cardCount": 60,  
 "basicPokemonCount": 8,  
 "aceSpecCount": 0,  
 "errors": \[\],  
 "warnings": \[\]  
}

## **DeckValidationError**

{  
 "code": "DECK\_TOO\_MANY\_COPIES",  
 "message": "No puedes tener más de 4 copias de Pikachu.",  
 "cardId": "xy1-42",  
 "cardName": "Pikachu"  
}

## **Reglas de validación**

* El mazo debe tener exactamente 60 cartas.  
* Debe tener al menos 1 Pokémon Básico.  
* Máximo 4 copias con el mismo nombre.  
* Las Energías Básicas no tienen límite de copias.  
* Máximo 1 carta de AS TÁCTICO en todo el mazo.  
* El set base obligatorio es `xy1`.

## **DeckValidationErrorCode**

DECK\_INVALID\_SIZE  
DECK\_MISSING\_BASIC\_POKEMON  
DECK\_TOO\_MANY\_COPIES  
DECK\_TOO\_MANY\_ACE\_SPEC  
CARD\_NOT\_FOUND  
CARD\_NOT\_ALLOWED\_IN\_FORMAT

\---

\# 10-api-rest.md

\`\`\`md  
\# REST API Contract v1

\#\# Objetivo

Definir endpoints mínimos entre frontend y backend.

REST se usa para:

\- crear partidas,  
\- unirse a partidas,  
\- consultar vista,  
\- enviar acciones,  
\- consultar logs,  
\- manejar mazos.

WebSocket se usa para sincronización en tiempo real.

\#\# Games

\`\`\`http  
POST /api/games  
GET  /api/games/{gameId}/view  
POST /api/games/{gameId}/join  
POST /api/games/{gameId}/actions  
GET  /api/games/{gameId}/events

## **POST /api/games**

Request:

{  
 "schemaVersion": 1,  
 "playerId": "player-1",  
 "deckId": "deck-1"  
}

Response:

{  
 "schemaVersion": 1,  
 "gameId": "game-123",  
 "status": "WAITING",  
 "playerId": "player-1"  
}

## **POST /api/games/{gameId}/join**

Request:

{  
 "schemaVersion": 1,  
 "playerId": "player-2",  
 "deckId": "deck-2"  
}

Response:

{  
 "schemaVersion": 1,  
 "gameId": "game-123",  
 "status": "SETUP"  
}

## **GET /api/games/{gameId}/view**

Query params:

playerId=player-1

Response:

{  
 "schemaVersion": 1,  
 "view": {}  
}

## **POST /api/games/{gameId}/actions**

Request:

{  
 "schemaVersion": 1,  
 "actionId": "a-001",  
 "gameId": "game-123",  
 "playerId": "player-1",  
 "type": "ATTACH\_ENERGY",  
 "payload": {  
   "energyCardInstanceId": "ci-001",  
   "targetPokemonId": "p-001"  
 }  
}

Response:

{  
 "schemaVersion": 1,  
 "success": true,  
 "gameId": "game-123",  
 "stateVersion": 2,  
 "events": \[\],  
 "error": null  
}

## **Decks**

GET    /api/decks  
POST   /api/decks  
GET    /api/decks/{deckId}  
PUT    /api/decks/{deckId}  
DELETE /api/decks/{deckId}  
POST   /api/decks/{deckId}/validate

## **Cards**

GET  /api/cards  
GET  /api/cards/{cardId}  
POST /api/cards/cache/sync

## **Reglas**

* Toda acción de juego entra por `/actions`.  
* Ningún endpoint debe modificar el estado de partida fuera del Game Engine.  
* `/view` siempre devuelve `GameView`, nunca `GameState`.  
* Los endpoints de cartas leen del caché local.

\---

\# 11-websocket.md

\`\`\`md  
\# WebSocket Contract v1

\#\# Objetivo

Sincronizar estado y eventos en tiempo real entre servidor y clientes.

El frontend puede enviar acciones por REST en la primera versión.  
WebSocket se usa para recibir eventos y vistas actualizadas.

\#\# Canales

\`\`\`text  
/topic/games/{gameId}/public  
/user/queue/games/{gameId}/private

## **Mensaje público: GAME\_EVENTS**

{  
 "schemaVersion": 1,  
 "messageType": "GAME\_EVENTS",  
 "gameId": "game-123",  
 "stateVersion": 10,  
 "events": \[\]  
}

## **Mensaje privado: GAME\_VIEW\_UPDATED**

{  
 "schemaVersion": 1,  
 "messageType": "GAME\_VIEW\_UPDATED",  
 "gameId": "game-123",  
 "viewerPlayerId": "player-1",  
 "stateVersion": 10,  
 "view": {}  
}

## **Mensaje privado: ACTION\_REJECTED**

{  
 "schemaVersion": 1,  
 "messageType": "ACTION\_REJECTED",  
 "gameId": "game-123",  
 "viewerPlayerId": "player-1",  
 "stateVersion": 10,  
 "error": {  
   "code": "INVALID\_PHASE",  
   "message": "No puedes realizar esta acción en la fase actual."  
 }  
}

## **Mensaje público: PLAYER\_CONNECTION\_CHANGED**

{  
 "schemaVersion": 1,  
 "messageType": "PLAYER\_CONNECTION\_CHANGED",  
 "gameId": "game-123",  
 "playerId": "player-2",  
 "isConnected": false  
}

## **Reglas**

* Los eventos públicos no deben incluir información oculta.  
* Las vistas privadas se envían por canal privado.  
* Cada jugador recibe su propia `GameView`.  
* Ante reconexión, el backend debe enviar la última `GameView` del jugador.  
* El cliente no debe reconstruir reglas; solo renderiza lo recibido.

\---

\# 12-visibility-rules.md

\`\`\`md  
\# Visibility Rules v1

\#\# Objetivo

Definir qué información puede recibir cada jugador.

Estas reglas son obligatorias para evitar filtrar información oculta.

\#\# Visible para ambos jugadores

\- Pokémon Activo de ambos jugadores.  
\- Pokémon en Banca de ambos jugadores.  
\- Daño sobre Pokémon en juego.  
\- Condiciones especiales sobre Pokémon en juego.  
\- Energías unidas.  
\- Herramientas unidas.  
\- Evoluciones visibles.  
\- Estadio activo.  
\- Pilas de descarte.  
\- Cantidad de cartas en cada mano.  
\- Cantidad de cartas en cada mazo.  
\- Cantidad de cartas de Premio restantes.  
\- Log público de eventos.

\#\# Visible solo para el jugador dueño

\- Su mano completa.  
\- Cartas robadas por él.  
\- Cartas de Premio cuando las toma.  
\- Decisiones privadas pendientes.

\#\# Nunca visible para el rival

\- Contenido de la mano.  
\- Orden del mazo.  
\- Identidad de cartas de Premio boca abajo.  
\- Cartas privadas elegidas durante búsquedas hasta que una regla indique revelarlas.

\#\# GameState vs GameView

\`GameState\` puede contener toda la información.

\`GameView\` debe filtrar información según \`viewerPlayerId\`.

\#\# Reglas de seguridad

\- El frontend no debe recibir \`GameState\`.  
\- El filtrado se hace en backend.  
\- No alcanza con ocultar datos por CSS.  
\- Si una carta está boca abajo, debe representarse como \`HiddenCardView\`.  
