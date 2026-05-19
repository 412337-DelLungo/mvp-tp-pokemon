export type GameActionType =
  | 'DRAW_CARD'
  | 'PUT_BASIC_ON_BENCH'
  | 'ATTACH_ENERGY'
  | 'EVOLVE_POKEMON'
  | 'PLAY_TRAINER'
  | 'RETREAT_ACTIVE'
  | 'DECLARE_ATTACK'
  | 'END_TURN'
  | 'CHOOSE_KNOCKOUT_REPLACEMENT'
  | 'USE_ABILITY';

export interface GameActionRequest {
  type: GameActionType;
  playerId: string;
  payload: Record<string, unknown>;
  clientRequestId: string;
}

export interface GameActionResponse {
  success: boolean;
  clientRequestId: string;
  publicState: unknown;
  privateState: unknown;
  events: GameEventModel[];
  error: GameErrorModel | null;
}

export interface GameErrorModel {
  code: string;
  message: string;
  details?: Record<string, unknown>;
}