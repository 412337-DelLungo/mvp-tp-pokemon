package com.pokemontcg.matches.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MatchPlayerJpaRepository extends JpaRepository<MatchPlayerEntity, UUID> {
}