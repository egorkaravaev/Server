package com.server.demo.entity.playerCompany;

import com.server.demo.entity.AbstractMission;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "missionCompanyInfo")
public class Mission extends AbstractMission implements Serializable{

    private Set<PlayerCompanyMission> playerCompanyMissions = new HashSet<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    public Set<PlayerCompanyMission> getPlayerCompanyMissions() {
        return playerCompanyMissions;
    }

    public void setPlayerCompanyMissions(Set<PlayerCompanyMission> playerCompanyMissions) {
        this.playerCompanyMissions = playerCompanyMissions;
    }
}
