package com.server.demo.entity.playerCompany;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mission_progress")
@IdClass(PlayerCompanyMissionId.class)
public class PlayerCompanyMission implements Serializable {

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "pl_id", referencedColumnName = "pk_player_id")
    private PlayerMissionInfo playerMI;

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "com_id", referencedColumnName = "pk_company_id")
    private Company company;

    @javax.persistence.Id
    @ManyToOne
    @JoinColumn(name = "mis_id", referencedColumnName = "pk_mission_id")
    private Mission mission;

    private int progress;
    private Integer energy;

    @Column(name = "progress")
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Column(name = "energy")
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public PlayerMissionInfo getPlayerMI() {
        return playerMI;
    }

    public void setPlayerMI(PlayerMissionInfo playerMI) {
        this.playerMI = playerMI;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
