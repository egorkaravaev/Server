package com.server.demo.controller;

import com.google.gson.Gson;
import com.server.demo.entity.playerCharacter.Player;
import com.server.demo.entity.playerCharacter.PlayerCharacter;
import com.server.demo.entity.playerCompany.PlayerCompanyMission;
import com.server.demo.entity.playerCompany.PlayerMissionInfo;
import com.server.demo.entity.resource.ResourceWrapper;
import com.server.demo.entity.resource.Resources;
import com.server.demo.message.Message;
import com.server.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageController {

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
            case 1111:
                Player player = gson.fromJson(message, Player.class);
                receivePlayer(player);
                break;
            case 1112:
                PlayerMissionInfo playerMissionInfo = gson.fromJson(message, PlayerMissionInfo.class);
                receiveMissionInfo(playerMissionInfo);
                break;
        }
    }

    private void receiveMissionInfo(PlayerMissionInfo player) {
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
            receiveMissionInfo(playerMI);
        } else {
            for (PlayerCompanyMission playerCompanyMission : playerMI.getPlayerCompanyMissions()) {
                playerCompanyMission.setPlayerMI(null);
                playerCompanyMission.getCompany().setPlayerCompanyMissions(null);
                playerCompanyMission.getMission().setPlayerCompanyMissions(null);
            }
            playerMI.setType(null);
            String jsonMessage = gson.toJson(playerMI);
            Message message = new Message(1112, jsonMessage);
            sendToServer(message);
        }
    }

    private void setCompanyMissionValues(long company, long mission, PlayerMissionInfo playerMI) {
        PlayerCompanyMission playerCompanyMission = new PlayerCompanyMission();
        playerCompanyMission.setPlayerMI(playerMI);
        playerCompanyMission.setCompany(companyService.getCompany(company));
        playerCompanyMission.setMission(missionService.getMission(mission));
        playerCompanyMission.setEnergy((int) (company + 1));
        playerMI.getPlayerCompanyMissions().add(playerCompanyMission);
    }

    private void receivePlayer(Player player){
        playerService.setType(player.getId(), "playerInfo");
        if(!playerService.playerExist(player.getId())){
            for (long i = 1; i < 9; i++){
                PlayerCharacter playerCharacter = new PlayerCharacter();
                playerCharacter.setPlayer(player);
                playerCharacter.setCharacter(characterService.getById(i));
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
            }
            String jsonMessage = gson.toJson(existingPlayer);
            Message message = new Message(1111, jsonMessage);
            sendToServer(message);
        }
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