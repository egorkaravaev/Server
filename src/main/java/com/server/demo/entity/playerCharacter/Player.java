package com.server.demo.entity.playerCharacter;

import com.server.demo.entity.AbstractPlayer;
import com.server.demo.entity.resource.ResourceWrapper;
import com.server.demo.entity.resource.Resources;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "playerInfo")
public class Player extends AbstractPlayer implements Serializable{

    private String name;
    private int evolution;
    private int level = 1;
    private int experience;
    private Set<PlayerCharacter> playerCharacters = new HashSet<>();
    private Resources resources;
    private List<ResourceWrapper> resourceWrappers = new ArrayList<>();

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "experience")
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Column(name = "evolution")
    public int getEvolution() {
        return evolution;
    }

    public void setEvolution(int evolution) {
        this.evolution = evolution;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    public Set<PlayerCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(Set<PlayerCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    @Transient
    public List<ResourceWrapper> getResourceWrappers() {
        return resourceWrappers;
    }

    public void setResourceWrappers(List<ResourceWrapper> resourceWrappers) {
        this.resourceWrappers = resourceWrappers;
    }

}
