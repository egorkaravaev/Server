package com.server.demo.entity.missionBattle;

import com.server.demo.entity.Character;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "enemy")
public class Enemy implements Serializable{

    private Long id;
    private int rank;
    private int level;
    private int stars;

    private Character character;

    private Set<MissionBattle> enemy1 = new HashSet<>();
    private Set<MissionBattle> enemy2 = new HashSet<>();
    private Set<MissionBattle> enemy3 = new HashSet<>();
    private Set<MissionBattle> enemy4 = new HashSet<>();
    private Set<MissionBattle> enemy5 = new HashSet<>();

    @Id
    @Column(name = "pk_enemy_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "stars")
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @OneToMany(mappedBy = "enemy1")
    public Set<MissionBattle> getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(Set<MissionBattle> enemy1) {
        this.enemy1 = enemy1;
    }

    @OneToMany(mappedBy = "enemy2")
    public Set<MissionBattle> getEnemy2() {
        return enemy2;
    }

    public void setEnemy2(Set<MissionBattle> enemy2) {
        this.enemy2 = enemy2;
    }

    @OneToMany(mappedBy = "enemy3")
    public Set<MissionBattle> getEnemy3() {
        return enemy3;
    }

    public void setEnemy3(Set<MissionBattle> enemy3) {
        this.enemy3 = enemy3;
    }

    @OneToMany(mappedBy = "enemy4")
    public Set<MissionBattle> getEnemy4() {
        return enemy4;
    }

    public void setEnemy4(Set<MissionBattle> enemy4) {
        this.enemy4 = enemy4;
    }

    @OneToMany(mappedBy = "enemy5")
    public Set<MissionBattle> getEnemy5() {
        return enemy5;
    }

    public void setEnemy5(Set<MissionBattle> enemy5) {
        this.enemy5 = enemy5;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "enemy_info_id")
    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
    //    public EnemyInfo getEnemyInfo() {
//        return enemyInfo;
//    }
//
//    public void setEnemyInfo(EnemyInfo enemyInfo) {
//        this.enemyInfo = enemyInfo;
//    }
}
