import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class DeckBuilderFacadeService {
  cards: { cardId: string; quantity: number }[] = [];

  addCard(cardId: string): void {
    const existing = this.cards.find(c => c.cardId === cardId);
    if (existing) {
      existing.quantity++;
    } else {
      this.cards.push({ cardId, quantity: 1 });
    }
  }

  removeCard(cardId: string): void {
    this.cards = this.cards.filter(c => c.cardId !== cardId);
  }

  getTotalCards(): number {
    return this.cards.reduce((sum, c) => sum + c.quantity, 0);
  }

  validate(): void {
    console.log('Validating deck with cards:', this.cards);
  }
}