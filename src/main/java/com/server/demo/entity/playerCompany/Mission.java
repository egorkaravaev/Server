package com.server.demo.entity.playerCompany;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mission")
public class Mission implements Serializable{

    private Long id;
    private String name;
    private String description;
    private Set<PlayerCompanyMission> playerCompanyMissions = new HashSet<>();

    @Id
    @Column(name = "pk_mission_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    public Set<PlayerCompanyMission> getPlayerCompanyMissions() {
        return playerCompanyMissions;
    }

    public void setPlayerCompanyMissions(Set<PlayerCompanyMission> playerCompanyMissions) {
        this.playerCompanyMissions = playerCompanyMissions;
    }
}
