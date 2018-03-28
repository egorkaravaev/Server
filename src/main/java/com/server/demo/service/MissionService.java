package com.server.demo.service;

import com.server.demo.entity.AbstractMission;
import com.server.demo.entity.playerCompany.Mission;
import com.server.demo.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MissionService {

    @Autowired
    MissionRepository missionRepository;

    @Transactional
    public AbstractMission getMission(Long id){
        return missionRepository.findById(id).get();
    }

    @Transactional
    public void setType(Long id, String type) {
        missionRepository.setType(id, type);
    }
}
