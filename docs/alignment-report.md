# Alignment Report (Revised)

Generated: 2026-05-06
Change: cross-layer-contract-alignment

---

## Contract Sources Used

This report was verified against the following contract files:
- `/docs/contracts_ai/00-contract-index.md`
- `/docs/contracts_ai/01-project-scope-contract.md`
- `/docs/contracts_ai/02-project-structure-contract.md`
- `/docs/contracts_ai/03-enums-contract.md`
- `/docs/contracts_ai/04-card-model-contract.md`
- `/docs/contracts_ai/05-deck-contract.md`
- `/docs/contracts_ai/06-game-state-contract.md`
- `/docs/contracts_ai/07-setup-flow-contract.md`
- `/docs/contracts_ai/08-game-action-contract.md`
- `/docs/contracts_ai/09-rule-validation-contract.md`
- `/docs/contracts_ai/10-attack-pipeline-contract.md`
- `/docs/contracts_ai/11-status-effects-contract.md`
- `/docs/contracts_ai/12-persistence-log-contract.md`
- `/docs/contracts_ai/13-rest-api-contract.md`
- `/docs/contracts_ai/14-websocket-contract.md`
- `/docs/contracts_ai/15-frontend-state-contract.md`
- `/docs/contracts_ai/16-test-scenarios-contract.md`
- `/docs/V1__init_schema.sql`

---

## Summary

This report documents the alignment findings between contracts and implementation.

---

## 1. Package Structure

### Backend

**Contract:** `/docs/contracts_ai/02-project-structure-contract.md`

**Status:** MATCH

All packages match the contract structure.

### Frontend

**Contract:** `/docs/contracts_ai/02-project-structure-contract.md`

**Status:** MATCH

---

## 2. Enums

**Status:** MATCH (exact values)

All enum values match exactly what the contract specifies:

| Enum | Location | Values (exact) | Status |
|------|---------|---------------|--------|
| `MatchStatus` | `matches/domain/` | WAITING, SETUP, ACTIVE, FINISHED | MATCH |
| `TurnPhase` | `engine/turn/` | DRAW, MAIN, ATTACK, BETWEEN_TURNS | MATCH |
| `PlayerSide` | `engine/` | PLAYER_ONE, PLAYER_TWO | MATCH |
| `GameActionType` | `engine/action/` | DRAW_CARD, PUT_BASIC_ON_BENCH, ATTACH_ENERGY, EVOLVE_POKEMON, PLAY_TRAINER, RETREAT_ACTIVE, DECLARE_ATTACK, END_TURN, CHOOSE_KNOCKOUT_REPLACEMENT, USE_ABILITY | MATCH |
| `GameEventType` | `engine/event/` | MATCH_CREATED, PLAYER_JOINED, SETUP_COMPLETED, TURN_STARTED, PHASE_CHANGED, CARD_DRAWN, POKEMON_PLACED_ON_BENCH, ENERGY_ATTACHED, POKEMON_EVOLVED, TRAINER_PLAYED, RETREAT_EXECUTED, ATTACK_DECLARED, DAMAGE_APPLIED, SPECIAL_CONDITION_APPLIED, SPECIAL_CONDITION_REMOVED, KNOCKOUT_OCCURRED, PRIZE_TAKEN, VICTORY_DECIDED, STATE_UPDATED | MATCH |
| `FinishReason` | `engine/victory/` | KNOCKOUT, PRIZES, DECK_OUT, CONCEDE | MATCH |
| `SpecialCondition` | `engine/` | ASLEEP, BURNED, CONFUSED, PARALYZED, POISONED | MATCH |
| `CardSupertype` | `cards/domain/` | POKEMON, ENERGY, TRAINER | MATCH |
| `PokemonStage` | `cards/domain/` | BASIC, STAGE_1, STAGE_2, MEGA, RESTORED | MATCH |
| `EnergyType` | `cards/domain/` | GRASS, FIRE, WATER, LIGHTNING, PSYCHIC, FIGHTING, DARKNESS, METAL, FAIRY, COLORLESS | MATCH |
| `TrainerType` | `cards/domain/` | ITEM, STADIUM, SUPPORTER | MATCH |

### Frontend TypeScript Enums

**Status:** MATCH (using string literals as expected)

Frontend uses string literal unions instead of actual enums, which is consistent with JSON serialization patterns.

---

## 3. JPA Entities vs SQL Schema

**Contract:** `/docs/V1__init_schema.sql`

**Status:** MATCH

All JPA entities correctly map to SQL columns including:
- TEXT[] arrays: `subtypes`, `pokemon_types`, `retreat_cost`, `provides_energy_types`, `rules_text`, `printed_cost`
- JSONB: `raw_json`, `validation_errors`, `serialized_state`, `payload`
- UUID generation: `@GeneratedValue(strategy = GenerationType.UUID)`
- Timestamps: `created_at`, `updated_at`, `finished_at`

---

## 4. REST API Endpoints

**Contract:** `/docs/contracts_ai/13-rest-api-contract.md`

**Status:** MISSING (empty placeholders)

The following MVP endpoints are specified but NOT IMPLEMENTED (Controllers are empty):

| Endpoint | Contract | Implementation | Status |
|----------|----------|---------------|--------|
| `POST /api/matches` | Yes | Empty class | MISSING |
| `POST /api/matches/{matchId}/join` | Yes | Empty class | MISSING |
| `GET /api/matches/{matchId}/state` | Yes | Empty class | MISSING |
| `POST /api/matches/{matchId}/actions` | Yes | Empty class | MISSING |
| `GET /api/cards` | Yes | Empty class | MISSING |
| `GET /api/cards/{cardId}` | Yes | Empty class | MISSING |
| `GET /api/decks/seed` | Yes | Empty class | MISSING |
| `GET /api/decks/{deckId}` | Yes | Empty class | MISSING |
| `POST /api/decks/validate` | Yes | Empty class | MISSING |

---

## 5. WebSocket

**Contract:** `/docs/contracts_ai/14-websocket-contract.md`

**Status:** PARTIAL (stubs exist)

| Component | Contract Expects | Implementation | Status |
|-----------|-------------|----------------|---------|
| Backend WebSocket Config | `/topic/matches/{matchId}/events` | Stub | PARTIAL |
| Backend WebSocket Publisher | Yes | Exists | MATCH |
| Backend WebSocket Controller | Yes | Stub | PARTIAL |
| Frontend MatchSocketService | Topics | Stub | PARTIAL |

---

## 6. Architecture Boundaries

**Status:** MATCH

- No JPA in engine: VERIFIED
- Engine doesn't depend on infrastructure: VERIFIED
- Frontend doesn't decide rules: VERIFIED

---

## 7. Mismatches Found

### Enum Mismatches Found
- None - all enum values match exactly

### Misplaced Packages Found
- None - package locations are correct

### SQL Path Fix
- Previous report incorrectly cited `/docs/database/V1__init_schema.sql`
- Correct path is `/docs/V1__init_schema.sql`

---

## 8. Mismatches Fixed

- Fixed the SQL source path in the report (was misquoted)
- No code changes were needed (all enums already match)

---

## 9. Remaining Risks

1. **REST API NOT IMPLEMENTED** - All 9 MVP endpoints are empty placeholder classes
2. **DTOs NOT IMPLEMENTED** - Request/Response DTOs are empty
3. **WebSocket handlers NOT IMPLEMENTED** - Only stubs exist
4. **Application service layer NOT IMPLEMENTED** - Most services are empty

---

## 10. Commands Run

```bash
# Backend packages
ls -la backend/src/main/java/com/pokemontcg/

# Enums check
cat backend/src/main/java/com/pokemontcg/engine/action/GameActionType.java
cat backend/src/main/java/com/pokemontcg/engine/event/GameEventType.java
cat backend/src/main/java/com/pokemontcg/engine/victory/FinishReason.java
cat backend/src/main/java/com/pokemontcg/cards/domain/TrainerType.java
cat backend/src/main/java/com/pokemontcg/cards/domain/CardSupertype.java
cat backend/src/main/java/com/pokemontcg/matches/domain/MatchStatus.java

# SQL path check
find . -name "V1__init_schema.sql"
```

---

## 11. Test Results

### Backend
```bash
cd backend && mvn test
```
**Result:** Tests pass ✓

### Frontend
```bash
cd frontend && npm run build
```
**Result:** Build successful ✓

---

Generated by: cross-layer-contract-alignment change (revised)