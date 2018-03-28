package com.server.demo.entity.missionBattle;

import java.io.Serializable;

public class MissionBattleId implements Serializable {

    private Long missionBI;
    private Long battle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionBattleId that = (MissionBattleId) o;

        if (missionBI != null ? !missionBI.equals(that.missionBI) : that.missionBI != null) return false;
        return battle != null ? battle.equals(that.battle) : that.battle == null;
    }

    @Override
    public int hashCode() {
        int result = missionBI != null ? missionBI.hashCode() : 0;
        result = 31 * result + (battle != null ? battle.hashCode() : 0);
        return result;
    }
}
