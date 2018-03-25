package com.server.demo.service;

import com.server.demo.entity.playerCharacter.PlayerCharacter;
import com.server.demo.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    ProgressRepository progressRepository;

    @Transactional
    public List<PlayerCharacter> getAll(){
        return progressRepository.findAll();
    }
}
