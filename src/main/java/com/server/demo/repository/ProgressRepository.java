package com.server.demo.repository;

import com.server.demo.entity.playerCharacter.PlayerCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<PlayerCharacter, Long> {
}
