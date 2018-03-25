package com.server.demo.repository;

import com.server.demo.entity.AbstractPlayer;
import com.server.demo.entity.playerCharacter.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<AbstractPlayer, Long> {

    @Modifying
    @Query("update AbstractPlayer player set player.type =:pType where player.id =:entryId")
    void setType(@Param("entryId") Long id, @Param("pType") String type);
}
