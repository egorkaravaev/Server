package com.server.demo.entity.resource;

import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resources {

    private Long id;
    private int ceramite;
    private int steel;
    private int chemical;
    private int crystal;
    private int battery;
    private int chip;
    private int book;
    private int steroid;
    private int gear;

    public Resources() {
    }

    public Resources(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "pk_resources_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ceramite")
    public int getCeramite() {
        return ceramite;
    }

    public void setCeramite(int ceramite) {
        this.ceramite = ceramite;
    }

    @Column(name = "steel")
    public int getSteel() {
        return steel;
    }

    public void setSteel(int steel) {
        this.steel = steel;
    }

    @Column(name = "chemical")
    public int getChemical() {
        return chemical;
    }

    public void setChemical(int chemical) {
        this.chemical = chemical;
    }

    @Column(name = "crystal")
    public int getCrystal() {
        return crystal;
    }

    public void setCrystal(int crystal) {
        this.crystal = crystal;
    }

    @Column(name = "battery")
    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    @Column(name = "chip")
    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        this.chip = chip;
    }

    @Column(name = "book")
    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    @Column(name = "steroid")
    public int getSteroid() {
        return steroid;
    }

    public void setSteroid(int steroid) {
        this.steroid = steroid;
    }

    @Column(name = "gear")
    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }
}
