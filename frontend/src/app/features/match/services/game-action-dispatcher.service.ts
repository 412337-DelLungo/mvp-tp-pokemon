import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class GameActionDispatcherService {
  dispatchAction(actionType: string, payload: Record<string, unknown>): void {
    console.log('Dispatching action:', actionType, payload);
  }

  drawCard(): void {
    this.dispatchAction('DRAW_CARD', {});
  }

  endTurn(): void {
    this.dispatchAction('END_TURN', {});
  }

  attachEnergy(energyCardInstanceId: string, targetPokemonInstanceId: string): void {
    this.dispatchAction('ATTACH_ENERGY', { energyCardInstanceId, targetPokemonInstanceId });
  }

  declareAttack(attackerPokemonInstanceId: string, attackIndex: number): void {
    this.dispatchAction('DECLARE_ATTACK', { attackerPokemonInstanceId, attackIndex });
  }
}