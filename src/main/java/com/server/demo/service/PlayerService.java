package com.server.demo.service;

import com.server.demo.entity.AbstractPlayer;
import com.server.demo.entity.playerCharacter.Player;
import com.server.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Transactional
    public List<AbstractPlayer> getAllPlayers(){
        return playerRepository.findAll();
    }

    @Transactional
    public AbstractPlayer getPlayer(Long id){
        return playerRepository.findById(id).get();
    }

    @Transactional
    public void deletePlayer(Long id){
        playerRepository.deleteById(id);
    }

    @Transactional
    public void savePlayer(AbstractPlayer player){
        playerRepository.save(player);
    }

    @Transactional
    public boolean playerExist(Long id){
        return playerRepository.existsById(id);
    }

    @Transactional
    public void setType(Long id, String type){
        playerRepository.setType(id, type);
    }

}
