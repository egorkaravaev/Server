package com.server.demo.controller;

import com.google.gson.Gson;
import com.server.demo.entity.CharacterName;
import com.server.demo.entity.missionBattle.MissionBattle;
import com.server.demo.entity.missionBattle.MissionBattleInfo;
import com.server.demo.entity.playerCharacter.Player;
import com.server.demo.entity.playerCharacter.PlayerCharacter;
import com.server.demo.entity.playerCompany.Mission;
import com.server.demo.entity.playerCompany.PlayerCompanyMission;
import com.server.demo.entity.playerCompany.PlayerMissionInfo;
import com.server.demo.entity.resource.ResourceWrapper;
import com.server.demo.entity.resource.Resources;
import com.server.demo.message.Message;
import com.server.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageController {

    private static final int LOGIN_IN = 1111;
    private static final int COMPANY_MISSION_INFO = 1112;
    private static final int MISSION_BATTLE_INFO = 1114;

    @Autowired
    PlayerService playerService;
    @Autowired
    CharacterService characterService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    CompanyService companyService;
    @Autowired
    MissionService missionService;

    private WebSocketSession session;
    private Gson gson = new Gson();


    public void handleMessage(String message, WebSocketSession session) {
        this.session = session;
        Message messageObject = gson.fromJson(message, Message.class);
        handleMessageType(messageObject.getType(), messageObject.getMessage());
    }

    private void handleMessageType(int type, String message){
        switch (type) {
            case LOGIN_IN:
                Player player = gson.fromJson(message, Player.class);
                receivePlayer(player);
                break;
            case COMPANY_MISSION_INFO:
                PlayerMissionInfo playerMissionInfo = gson.fromJson(message, PlayerMissionInfo.class);
                receiveAllMissionsInfo(playerMissionInfo);
                break;
            case MISSION_BATTLE_INFO:
                MissionBattleInfo missionBattleInfo = gson.fromJson(message, MissionBattleInfo.class);
                receiveMissionInfo(missionBattleInfo);
                break;
        }
    }

    private void receiveMissionInfo(MissionBattleInfo missionBattleInfo) {
        missionService.setType(missionBattleInfo.getId(), "missionBattleInfo");
        MissionBattleInfo missionBI = (MissionBattleInfo) missionService.getMission(missionBattleInfo.getId());
        missionBI.setType(null);
        for (MissionBattle missionBattle: missionBI.getMissionBattles()) {
            missionBattle.setMissionBI(null);
            missionBattle.getBattle().setMissionBattles(null);
            if (missionBattle.getEnemy1() != null){
                missionBattle.getEnemy1().setEnemy1(null);
                missionBattle.getEnemy1().setEnemy2(null);
                missionBattle.getEnemy1().setEnemy3(null);
                missionBattle.getEnemy1().setEnemy4(null);
                missionBattle.getEnemy1().setEnemy5(null);
                missionBattle.getEnemy1().getCharacter().setPlayerCharacters(null);
                for (CharacterName characterName : missionBattle.getEnemy1().getCharacter().getNames()) {
                    characterName.setCharacters(null);
                    characterName.setId(null);
                }
            }
            if (missionBattle.getEnemy2() != null){
                missionBattle.getEnemy2().setEnemy1(null);
                missionBattle.getEnemy2().setEnemy2(null);
                missionBattle.getEnemy2().setEnemy3(null);
                missionBattle.getEnemy2().setEnemy4(null);
                missionBattle.getEnemy2().setEnemy5(null);
                missionBattle.getEnemy2().getCharacter().setPlayerCharacters(null);
                for (CharacterName characterName : missionBattle.getEnemy2().getCharacter().getNames()) {
                    characterName.setCharacters(null);
                    characterName.setId(null);
                }
            }
            if (missionBattle.getEnemy3() != null){
                missionBattle.getEnemy3().setEnemy1(null);
                missionBattle.getEnemy3().setEnemy2(null);
                missionBattle.getEnemy3().setEnemy3(null);
                missionBattle.getEnemy3().setEnemy4(null);
                missionBattle.getEnemy3().setEnemy5(null);
                missionBattle.getEnemy3().getCharacter().setPlayerCharacters(null);
                for (CharacterName characterName : missionBattle.getEnemy3().getCharacter().getNames()) {
                    characterName.setCharacters(null);
                    characterName.setId(null);
                }
            }
            if (missionBattle.getEnemy4() != null){
                missionBattle.getEnemy4().setEnemy1(null);
                missionBattle.getEnemy4().setEnemy2(null);
                missionBattle.getEnemy4().setEnemy3(null);
                missionBattle.getEnemy4().setEnemy4(null);
                missionBattle.getEnemy4().setEnemy5(null);
                missionBattle.getEnemy4().getCharacter().setPlayerCharacters(null);
                for (CharacterName characterName : missionBattle.getEnemy4().getCharacter().getNames()) {
                    characterName.setCharacters(null);
                    characterName.setId(null);
                }
            }
            if (missionBattle.getEnemy5() != null){
                missionBattle.getEnemy5().setEnemy1(null);
                missionBattle.getEnemy5().setEnemy2(null);
                missionBattle.getEnemy5().setEnemy3(null);
                missionBattle.getEnemy5().setEnemy4(null);
                missionBattle.getEnemy5().setEnemy5(null);
                missionBattle.getEnemy5().getCharacter().setPlayerCharacters(null);
                for (CharacterName characterName : missionBattle.getEnemy5().getCharacter().getNames()) {
                    characterName.setCharacters(null);
                    characterName.setId(null);
                }
            }
        }
        String jsonMessage = gson.toJson(missionBI);
        Message message = new Message(1114, jsonMessage);
        sendToServer(message);
    }

    private void receiveAllMissionsInfo(PlayerMissionInfo player) {
        playerService.setType(player.getId(), "playerMissionInfo");

        PlayerMissionInfo playerMI = (PlayerMissionInfo) playerService.getPlayer(player.getId());
        if(playerMI.getPlayerCompanyMissions().isEmpty()){
            for (long company = 1; company < 9; company++){
                for (long mission = 1; mission < 9; mission++){
                    setCompanyMissionValues(company, mission, playerMI);
                }
                //for full DB
//                if (company == 1){
//                    for (long mission = 1; mission < 9; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else if (company == 2){
//                    for (long mission = 9; mission < 18; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else if (company == 3){
//                    for (long mission = 18; mission < 27; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else if (company == 4){
//                    for (long mission = 27; mission < 36; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else if (company == 5){
//                    for (long mission = 36; mission < 45; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else if (company == 6){
//                    for (long mission = 45; mission < 54; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else if (company == 7){
//                    for (long mission = 54; mission < 63; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                } else {
//                    for (long mission = 64; mission < 73; mission++){
//                        setCompanyMissionValues(company, mission, playerMI);
//                    }
//                }
            }
            playerService.savePlayer(playerMI);
            receiveAllMissionsInfo(playerMI);
        } else {
            for (PlayerCompanyMission playerCompanyMission : playerMI.getPlayerCompanyMissions()) {
                playerCompanyMission.setPlayerMI(null);
                playerCompanyMission.getCompany().setPlayerCompanyMissions(null);
                playerCompanyMission.getMission().setPlayerCompanyMissions(null);
                playerCompanyMission.getMission().setType(null);
            }
            playerMI.setType("1113");
            String jsonMessage = gson.toJson(playerMI);
            Message message = new Message(1112, jsonMessage);
            sendToServer(message);
        }
    }

    private void setCompanyMissionValues(long company, long mission, PlayerMissionInfo playerMI) {
        missionService.setType(mission, "missionCompanyInfo");
        PlayerCompanyMission playerCompanyMission = new PlayerCompanyMission();
        playerCompanyMission.setPlayerMI(playerMI);
        playerCompanyMission.setCompany(companyService.getCompany(company));
        playerCompanyMission.setMission((Mission) missionService.getMission(mission));
        playerCompanyMission.setEnergy((int) (company + 1));
        if (company == 1L && mission == 1L) playerCompanyMission.setProgress(1);
        playerMI.getPlayerCompanyMissions().add(playerCompanyMission);
    }

    private void receivePlayer(Player player){
        playerService.setType(player.getId(), "playerInfo");
        if(!playerService.playerExist(player.getId())){
            for (Long id : generateRandomCharactersId()) {
                PlayerCharacter playerCharacter = new PlayerCharacter();
                playerCharacter.setPlayer(player);
                playerCharacter.setCharacter(characterService.getById(id));
                player.getPlayerCharacters().add(playerCharacter);
            }
            player.setType("playerInfo");
            playerService.savePlayer(player);
            resourceService.saveResource(new Resources(player.getId()));
            receivePlayer(player);
        } else {
            Resources resources = resourceService.getById(player.getId());
            List<ResourceWrapper> resourceWrappers = new ArrayList<>();

            Field[] fields = resources.getClass().getDeclaredFields();
            for (Field field: fields) {
                try {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    int fieldValue = ((Number)(field.get(resources))).intValue();
                    ResourceWrapper resourceWrapper = new ResourceWrapper(fieldName, fieldValue);
                    resourceWrappers.add(resourceWrapper);
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            resourceWrappers.removeIf(wrapper -> wrapper.getName().equals("id"));

            Player existingPlayer = (Player) playerService.getPlayer(player.getId());
            existingPlayer.setResourceWrappers(resourceWrappers);
            existingPlayer.setResources(null);
            existingPlayer.setType(null);

            for(PlayerCharacter playerCharacter : existingPlayer.getPlayerCharacters()) {
                playerCharacter.setPlayer(null);
                playerCharacter.getCharacter().setPlayerCharacters(null);
                for (CharacterName characterName : playerCharacter.getCharacter().getNames()){
                    characterName.setId(null);
                    characterName.setCharacters(null);
                }
            }
            String jsonMessage = gson.toJson(existingPlayer);
            Message message = new Message(1111, jsonMessage);
            sendToServer(message);
        }
    }

    private ArrayList<Long> generateRandomCharactersId() {
        ArrayList<Long> randomNumbers = new ArrayList<>();
        while (randomNumbers.size() < 5){
            boolean isExist = false;
            long randomNumber = 1 + (int) (Math.random() * 8);
            for (Long number : randomNumbers) {
                if (number == randomNumber) {
                    isExist = true;
                }
            }
            if (!isExist) {
                randomNumbers.add(randomNumber);
            }
        }
        return randomNumbers;
    }

    private void sendToServer(Message message) {
        String object = gson.toJson(message);
        WebSocketMessage<?> webSocketMessage = new TextMessage(object);
        try {
            session.sendMessage(webSocketMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}