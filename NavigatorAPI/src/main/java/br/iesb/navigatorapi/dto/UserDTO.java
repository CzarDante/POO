package br.iesb.navigatorapi.dto;

import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.UserEntity;

import java.util.ArrayList;

public class UserDTO {

    private String name;
    private InventoryEntity inventory = new InventoryEntity();

    private ArrayList<BoatDTO> boats;

    private String currentIslandID;
    private ArrayList<String> closeIslandsID = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryEntity getInventory() {
        return inventory;
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

    public void setCloseIslandsID(ArrayList<String> closeIslandsID) {
        this.closeIslandsID = closeIslandsID;
    }
}
