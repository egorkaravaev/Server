package com.server.demo.entity;

import com.server.demo.entity.playerCharacter.PlayerCharacter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "character")
public class Character {

    private Long id;
    private String description;
    private int fightStyle;
    private int attackEffect;
    private int firstDefEffect;
    private int secondDefEffect;

    private Set<CharacterName> names = new HashSet<>();
    private Set<PlayerCharacter> playerCharacters = new HashSet<>();

    @Id
    @Column(name = "pk_character_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "fight_style")
    public int getFightStyle() {
        return fightStyle;
    }

    public void setFightStyle(int fightStyle) {
        this.fightStyle = fightStyle;
    }

    @Column(name = "attack_effect")
    public int getAttackEffect() {
        return attackEffect;
    }

    public void setAttackEffect(int attackEffect) {
        this.attackEffect = attackEffect;
    }

    @Column(name = "first_def_effect")
    public int getFirstDefEffect() {
        return firstDefEffect;
    }

    public void setFirstDefEffect(int firstDefEffect) {
        this.firstDefEffect = firstDefEffect;
    }

    @Column(name = "second_def_effect")
    public int getSecondDefEffect() {
        return secondDefEffect;
    }

    public void setSecondDefEffect(int secondDefEffect) {
        this.secondDefEffect = secondDefEffect;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "character_to_name",
            joinColumns = { @JoinColumn(name = "c_id") },
            inverseJoinColumns = { @JoinColumn(name = "n_id") }
    )
    public Set<CharacterName> getNames() {
        return names;
    }

    public void setNames(Set<CharacterName> names) {
        this.names = names;
    }

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    public Set<PlayerCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(Set<PlayerCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }
}
