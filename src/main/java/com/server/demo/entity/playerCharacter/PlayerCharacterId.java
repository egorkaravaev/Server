package com.server.demo.entity.playerCharacter;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;

public class PlayerCharacterId implements Serializable{

    private Long player;
    private Long character;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerCharacterId that = (PlayerCharacterId) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        return character != null ? character.equals(that.character) : that.character == null;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (character != null ? character.hashCode() : 0);
        return result;
    }
}
