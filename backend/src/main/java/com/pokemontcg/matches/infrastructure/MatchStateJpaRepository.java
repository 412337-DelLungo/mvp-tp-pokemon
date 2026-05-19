package com.pokemontcg.matches.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MatchStateJpaRepository extends JpaRepository<MatchStateEntity, UUID> {
}