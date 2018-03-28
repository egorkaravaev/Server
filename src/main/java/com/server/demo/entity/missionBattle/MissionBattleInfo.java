package com.server.demo.entity.missionBattle;

import com.server.demo.entity.AbstractMission;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "missionBattleInfo")
public class MissionBattleInfo extends AbstractMission implements Serializable{

    private Set<MissionBattle> missionBattles = new HashSet<>();

    @OneToMany(mappedBy = "missionBI", cascade = CascadeType.ALL)
    public Set<MissionBattle> getMissionBattles() {
        return missionBattles;
    }

    public void setMissionBattles(Set<MissionBattle> missionBattles) {
        this.missionBattles = missionBattles;
    }
}
