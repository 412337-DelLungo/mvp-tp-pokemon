package com.pokemontcg.cards.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, String> {
}