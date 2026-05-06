# CardDefinition Contract v1

## Objetivo

`CardDefinition` representa los datos oficiales/cacheados de una carta.

Estos datos vienen de la API externa `pokemontcg.io` y se guardan en base de datos local. Durante una partida, el motor debe leer cartas desde el caché local, no desde la API externa.

## CardDefinition

```json
{
  "schemaVersion": 1,
  "cardId": "xy1-1",
  "setId": "xy1",
  "number": "1",
  "name": "Venusaur-EX",
  "normalizedName": "VENUSAUR-EX",
  "supertype": "POKEMON",
  "subtypes": ["BASIC", "EX"],
  "pokemonStage": "BASIC",
  "trainerSubtype": null,
  "energyTypes": [],
  "hp": 180,
  "types": ["GRASS"],
  "evolvesFrom": null,
  "evolvesTo": [],
  "attacks": [],
  "abilities": [],
  "weaknesses": [],
  "resistances": [],
  "retreatCost": ["COLORLESS", "COLORLESS", "COLORLESS"],
  "convertedRetreatCost": 3,
  "rules": [],
  "legalities": {},
  "images": {
    "small": "https://...",
    "large": "https://..."
  },
  "rawText": null
}
```

## **AttackDefinition**

```json
{
  "attackId": "xy1-1-attack-1",
  "name": "Poison Powder",
  "cost": ["GRASS", "COLORLESS", "COLORLESS"],
  "convertedEnergyCost": 3,
  "damageText": "60",
  "baseDamagePoints": 60,
  "damageModifierSymbol": null,
  "text": "Your opponent's Active Pokémon is now Poisoned.",
  "implementedEffectKey": "APPLY_POISON",
  "requiresTarget": true,
  "targetRule": "OPPONENT_ACTIVE"
}
```

## **AbilityDefinition**

```json
{
  "abilityId": "xy1-1-ability-1",
  "name": "Verdant Wind",
  "type": "ABILITY",
  "text": "Each of your Pokémon that has any Grass Energy attached to it can't be affected by any Special Conditions.",
  "implementedEffectKey": null
}
```

## **WeaknessDefinition**

```json
{
  "type": "FIRE",
  "value": "x2"
}
```

## **ResistanceDefinition**

```json
{
  "type": "WATER",
  "value": "-20"
}
```

## **Reglas**

* `cardId` identifica la carta oficial.
* `cardInstanceId` identifica una copia concreta dentro de una partida.
* Puede haber muchas instancias con el mismo `cardId`.
* El texto libre de ataques y habilidades se conserva, pero no se interpreta automáticamente.
* Los efectos implementados se resuelven mediante `implementedEffectKey`.
* Si un efecto todavía no está implementado, `implementedEffectKey` puede ser `null`.
