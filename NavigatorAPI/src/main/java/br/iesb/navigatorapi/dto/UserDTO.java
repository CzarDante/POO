package br.iesb.navigatorapi.dto;

import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.UserEntity;

import java.util.ArrayList;

public class UserDTO {

    private String name;
    private int money;
    private int wood;
    private int metal;
    private ArrayList<BoatEntity> boats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public ArrayList<BoatEntity> getBoats() {
        return boats;
    }

    public void setBoats(ArrayList<BoatEntity> boats) {
        this.boats = boats;
    }

}
