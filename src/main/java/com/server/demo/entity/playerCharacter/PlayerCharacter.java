package com.server.demo.entity.playerCharacter;

import com.server.demo.entity.Character;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "character_progress")
@IdClass(PlayerCharacterId.class)
public class PlayerCharacter implements Serializable{

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "p_id", referencedColumnName = "pk_player_id")
    private Player player;

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "c_id", referencedColumnName = "pk_character_id")
    private Character character;

    private int power = 100;
    private int stars;
    private int rank = 1;
    private int splinters;
    private String slots;

    @Column(name = "power")
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Column(name = "stars")
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Column(name = "splinters")
    public int getSplinters() {
        return splinters;
    }

    public void setSplinters(int splinters) {
        this.splinters = splinters;
    }

    @Column(name = "slots")
    public String getSlots() {
        return slots;
    }

    public void setSlots(String slots) {
        this.slots = slots;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
