import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class MatchFacadeService {
  matchId: string | null = null;
  playerId: string | null = null;

  createMatch(playerName: string, deckId: string): void {
    console.log('Creating match:', playerName, deckId);
  }

  joinMatch(matchId: string, playerName: string, deckId: string): void {
    console.log('Joining match:', matchId, playerName, deckId);
  }

  getMatchState(): void {
    console.log('Getting match state');
  }
}