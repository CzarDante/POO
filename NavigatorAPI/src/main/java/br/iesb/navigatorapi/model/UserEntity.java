package br.iesb.navigatorapi.model;

//import java.util.ArrayList;

import java.util.ArrayList;

public class UserEntity {
    private String name;
    private String token;
    private int money;
    private int wood;
    private int metal;
    private ArrayList<BoatEntity> boats = new ArrayList<BoatEntity>();

    public UserEntity(){
        this.setMoney(100);
        this.setWood(500);
        this.setMetal(500);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public void setBoats(BoatEntity boats) {
        this.boats.add(boats);
    }
}
