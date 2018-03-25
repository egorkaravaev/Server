package com.server.demo.entity.playerCompany;

import com.server.demo.entity.AbstractPlayer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "playerMissionInfo")
public class PlayerMissionInfo extends AbstractPlayer implements Serializable{

    private Set<PlayerCompanyMission> playerCompanyMissions = new HashSet<>();

    @OneToMany(mappedBy = "playerMI", cascade = CascadeType.ALL)
    public Set<PlayerCompanyMission> getPlayerCompanyMissions() {
        return playerCompanyMissions;
    }

    public void setPlayerCompanyMissions(Set<PlayerCompanyMission> playerCompanyMissions) {
        this.playerCompanyMissions = playerCompanyMissions;
    }
}
