# Enums Contract v1

## GameStatus

Estados generales de una partida.

```
WAITING
SETUP
ACTIVE
FINISHED
```

## TurnPhase

Fases del turno o puntos de espera del motor.

```
DRAW
MAIN
ATTACK
BETWEEN_TURNS
AWAITING_PLAYER_CHOICE
FINISHED
```

`AWAITING_PLAYER_CHOICE` se usa cuando el motor necesita una decisión del jugador, por ejemplo:

- elegir Pokémon Activo inicial,
- elegir Pokémon de reemplazo después de un knockout,
- elegir carta de Premio,
- elegir objetivo de ataque,
- resolver setup o mulligan.

## Zone

Zonas posibles de una carta durante la partida.

```
DECK
HAND
ACTIVE
BENCH
PRIZE
DISCARD
STADIUM
LOST_ZONE
```

## CardSupertype

Tipos principales de carta.

```
POKEMON
ENERGY
TRAINER
```

## PokemonStage

```
BASIC
STAGE_1
STAGE_2
MEGA
RESTORED
UNKNOWN
```

Para el MVP se soportan principalmente:

- BASIC
- STAGE_1
- STAGE_2

## TrainerSubtype

```
ITEM
SUPPORTER
STADIUM
POKEMON_TOOL
ACE_SPEC
FOSSIL
UNKNOWN
```

## EnergyType

```
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
```

## SpecialCondition

```
ASLEEP
BURNED
CONFUSED
PARALYZED
POISONED
```

## EventVisibility

```
PUBLIC
PRIVATE_TO_PLAYER
PRIVATE_TO_OPPONENT
SYSTEM_ONLY
```

## GameEndReason

```
PRIZE_CARDS_TAKEN
OPPONENT_HAS_NO_POKEMON
DECK_EMPTY_ON_DRAW
CONCEDE
SUDDEN_DEATH_RESOLVED
```

## PendingRequestType

```
CHOOSE_INITIAL_ACTIVE
CHOOSE_INITIAL_BENCH
CONFIRM_SETUP
CHOOSE_ATTACK_TARGET
CHOOSE_PRIZE_CARD
CHOOSE_NEW_ACTIVE_AFTER_KNOCKOUT
CHOOSE_CARDS_FROM_DECK
CHOOSE_CARDS_TO_DISCARD
RESPOND_TO_MULLIGAN_DRAW
```
