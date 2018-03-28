package com.server.demo.entity.missionBattle;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mission_battle")
@IdClass(MissionBattleId.class)
public class MissionBattle implements Serializable{

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "m_id", referencedColumnName = "pk_mission_id")
    private MissionBattleInfo missionBI;

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "b_id", referencedColumnName = "pk_battle_id")
    private Battle battle;

    @ManyToOne
    @JoinColumn(name = "enemy_1")
    private Enemy enemy1;

    @ManyToOne
    @JoinColumn(name = "enemy_2")
    private Enemy enemy2;

    @ManyToOne
    @JoinColumn(name = "enemy_3")
    private Enemy enemy3;

    @ManyToOne
    @JoinColumn(name = "enemy_4")
    private Enemy enemy4;

    @ManyToOne
    @JoinColumn(name = "enemy_5")
    private Enemy enemy5;

    public MissionBattleInfo getMissionBI() {
        return missionBI;
    }

    public void setMissionBI(MissionBattleInfo missionBI) {
        this.missionBI = missionBI;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Enemy getEnemy1() {
        return enemy1;
    }

    public void setEnemy1(Enemy enemy1) {
        this.enemy1 = enemy1;
    }

    public Enemy getEnemy2() {
        return enemy2;
    }

    public void setEnemy2(Enemy enemy2) {
        this.enemy2 = enemy2;
    }

    public Enemy getEnemy3() {
        return enemy3;
    }

    public void setEnemy3(Enemy enemy3) {
        this.enemy3 = enemy3;
    }

    public Enemy getEnemy4() {
        return enemy4;
    }

    public void setEnemy4(Enemy enemy4) {
        this.enemy4 = enemy4;
    }

    public Enemy getEnemy5() {
        return enemy5;
    }

    public void setEnemy5(Enemy enemy5) {
        this.enemy5 = enemy5;
    }
}
