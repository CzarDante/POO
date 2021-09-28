package br.iesb.navigatorapi.model;

//import java.util.ArrayList;

import br.iesb.navigatorapi.dto.BoatDTO;

import java.util.ArrayList;

public class UserEntity {
    private String name;
    private String token;
    /*
    private int money;
    private int wood;
    private int metal;
    */
    private InventoryEntity inventory = new InventoryEntity();
    private ArrayList<BoatDTO> boats = new ArrayList<BoatDTO>();

    /*
    public UserEntity(){
        this.setMoney(100);
        this.setWood(500);
        this.setMetal(500);
    }

     */

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

    /*
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

     */

    public InventoryEntity getInventory() {
        return this.inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public ArrayList<BoatDTO> getBoats() {
        return boats;
    }

    public void setBoats(ArrayList<BoatDTO> boats) {
        this.boats = boats;
    }
}
