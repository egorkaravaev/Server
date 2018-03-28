package com.server.demo.repository;

import com.server.demo.entity.AbstractMission;
import com.server.demo.entity.playerCompany.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<AbstractMission, Long>{

    @Modifying
    @Query("update AbstractMission mission set mission.type =:mType where mission.id =:entryId")
    void setType(@Param("entryId") Long id, @Param("mType") String type);
}
