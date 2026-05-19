package com.pokemontcg.decks.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DeckJpaRepository extends JpaRepository<DeckEntity, UUID> {
}