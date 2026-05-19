export type GameEventType =
  | 'MATCH_CREATED'
  | 'PLAYER_JOINED'
  | 'SETUP_COMPLETED'
  | 'TURN_STARTED'
  | 'PHASE_CHANGED'
  | 'CARD_DRAWN'
  | 'POKEMON_PLACED_ON_BENCH'
  | 'ENERGY_ATTACHED'
  | 'POKEMON_EVOLVED'
  | 'TRAINER_PLAYED'
  | 'RETREAT_EXECUTED'
  | 'ATTACK_DECLARED'
  | 'DAMAGE_APPLIED'
  | 'SPECIAL_CONDITION_APPLIED'
  | 'SPECIAL_CONDITION_REMOVED'
  | 'KNOCKOUT_OCCURRED'
  | 'PRIZE_TAKEN'
  | 'VICTORY_DECIDED'
  | 'STATE_UPDATED';

export interface GameEventModel {
  type: GameEventType;
  matchId: string;
  turnNumber: number;
  createdAt: string;
  message: string;
  payload?: Record<string, unknown>;
}