package com.pokemontcg.cards.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CardWeaknessJpaRepository extends JpaRepository<CardWeaknessEntity, UUID> {
}