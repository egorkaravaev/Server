package com.server.demo.entity.missionBattle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "battle")
public class Battle {

    private Long id;
    private Set<MissionBattle> missionBattles = new HashSet<>();

    @Id
    @Column(name = "pk_battle_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL)
    public Set<MissionBattle> getMissionBattles() {
        return missionBattles;
    }

    public void setMissionBattles(Set<MissionBattle> missionBattles) {
        this.missionBattles = missionBattles;
    }
}
