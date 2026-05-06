# Deck Contracts v1

## Objetivo

Definir contratos para construcción, validación y uso de mazos.

El Deck Builder trabaja con cartas cacheadas desde `pokemontcg.io`, usando el set obligatorio `xy1`.

## DeckSummary

```json
{
  "schemaVersion": 1,
  "deckId": "deck-1",
  "ownerPlayerId": "player-1",
  "name": "XY Grass Deck",
  "cardCount": 60,
  "isValid": true,
  "validationErrors": [],
  "createdAt": "2026-05-05T19:00:00Z",
  "updatedAt": "2026-05-05T19:00:00Z"
}
```

## **DeckDetail**

```json
{
  "schemaVersion": 1,
  "deckId": "deck-1",
  "ownerPlayerId": "player-1",
  "name": "XY Grass Deck",
  "cards": [],
  "validation": {}
}
```

## **DeckCardEntry**

```json
{
  "cardId": "xy1-1",
  "name": "Venusaur-EX",
  "count": 2,
  "supertype": "POKEMON",
  "subtypes": ["BASIC", "EX"]
}
```

## **DeckValidationResult**

```json
{
  "isValid": true,
  "cardCount": 60,
  "basicPokemonCount": 8,
  "aceSpecCount": 0,
  "errors": [],
  "warnings": []
}
```

## **DeckValidationError**

```json
{
  "code": "DECK_TOO_MANY_COPIES",
  "message": "No puedes tener más de 4 copias de Pikachu.",
  "cardId": "xy1-42",
  "cardName": "Pikachu"
}
```

## **Reglas de validación**

* El mazo debe tener exactamente 60 cartas.
* Debe tener al menos 1 Pokémon Básico.
* Máximo 4 copias con el mismo nombre.
* Las Energías Básicas no tienen límite de copias.
* Máximo 1 carta de AS TÁCTICO en todo el mazo.
* El set base obligatorio es `xy1`.

## **DeckValidationErrorCode**

```
DECK_INVALID_SIZE
DECK_MISSING_BASIC_POKEMON
DECK_TOO_MANY_COPIES
DECK_TOO_MANY_ACE_SPEC
CARD_NOT_FOUND
CARD_NOT_ALLOWED_IN_FORMAT
```
