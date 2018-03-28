package com.server.demo.service;

import com.server.demo.entity.Character;
import com.server.demo.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Transactional
    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    @Transactional
    public Character getById(Long id){
        return characterRepository.findById(id).get();
    }

    @Transactional
    public void saveCharacter(Character character){
        characterRepository.save(character);
    }

    @Transactional
    public void saveAllCharacters(List<Character> characters){
        characterRepository.saveAll(characters);
    }
}
