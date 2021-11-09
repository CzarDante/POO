package br.iesb.navigatorapi.model;

//import java.util.ArrayList;

import br.iesb.navigatorapi.dto.BoatDTO;

import java.util.ArrayList;

public class UserEntity {
    private String name;
    private String token;
    private InventoryEntity inventory = new InventoryEntity();
    private ArrayList<BoatDTO> boats = new ArrayList<BoatDTO>();
    private long lootCooldown;

    private String currentIslandID;
    private ArrayList<String> closeIslandsID = new ArrayList<String>();

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

    public String getCurrentIslandID() {
        return currentIslandID;
    }

    public void setCurrentIslandID(String currentIslandID) {
        this.currentIslandID = currentIslandID;
    }

    public ArrayList<String> getCloseIslandsID() {
        return closeIslandsID;
    }

    public void setCloseIslandsID(String closeIslandsID) {
        this.closeIslandsID.add(closeIslandsID);
    }

    public long getLootCooldown() {
        return lootCooldown;
    }

    public void setLootCooldown(int tempo) {
        this.lootCooldown = System.currentTimeMillis() + tempo * 60000;
    }
}
