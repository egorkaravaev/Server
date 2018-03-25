package com.server.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "player")
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AbstractPlayer {

    private Long id;
    private String type;

    @Id
    @Column(name = "pk_player_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "type", insertable = false, updatable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
