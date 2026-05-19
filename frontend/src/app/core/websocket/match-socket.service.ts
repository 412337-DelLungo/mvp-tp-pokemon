import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

export type ConnectionStatus = 'CONNECTED' | 'DISCONNECTED' | 'RECONNECTING';

@Injectable({ providedIn: 'root' })
export class MatchSocketService {
  private publicEvents = new Subject<unknown>();
  private privateState = new Subject<unknown>();
  private connectionStatus = new Subject<ConnectionStatus>();

  publicEvents$ = this.publicEvents.asObservable();
  privateState$ = this.privateState.asObservable();
  connectionStatus$ = this.connectionStatus.asObservable();

  connect(matchId: string, playerId: string): void {
    console.log('Connecting to match:', matchId, 'player:', playerId);
    this.connectionStatus.next('CONNECTED');
  }

  disconnect(): void {
    this.connectionStatus.next('DISCONNECTED');
  }

  subscribeToMatch(matchId: string): void {
    console.log('Subscribing to match events:', matchId);
  }

  sendAction(matchId: string, action: unknown): void {
    console.log('Sending action:', action);
  }
}