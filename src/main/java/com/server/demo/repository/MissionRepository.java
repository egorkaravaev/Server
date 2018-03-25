package com.server.demo.repository;

import com.server.demo.entity.playerCompany.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>{
}
