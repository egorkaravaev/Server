package com.server.demo.entity.playerCompany;

import java.io.Serializable;

public class PlayerCompanyMissionId implements Serializable {

    private Long playerMI;
    private Long company;
    private Long mission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerCompanyMissionId that = (PlayerCompanyMissionId) o;

        if (playerMI != null ? !playerMI.equals(that.playerMI) : that.playerMI != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return mission != null ? mission.equals(that.mission) : that.mission == null;
    }

    @Override
    public int hashCode() {
        int result = playerMI != null ? playerMI.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (mission != null ? mission.hashCode() : 0);
        return result;
    }
}
