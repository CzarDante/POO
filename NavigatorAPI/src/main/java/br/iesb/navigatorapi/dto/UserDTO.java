package br.iesb.navigatorapi.dto;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.island.IslandEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String name;
    private InventoryEntity inventory = new InventoryEntity();
    private String currentIsland;
    //private IslandEntity currentIsland;
    //private List<IslandEntity> closeIslands = new ArrayList<IslandEntity>();
    private List<String> closeIslands = new ArrayList<String>();

    private ArrayList<BoatDTO> boats;

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

    public String getCurrentIsland() {
        return currentIsland;
    }

    public void setCurrentIsland(IslandEntity currentIsland) {
        this.currentIsland = currentIsland.getId();
    }

    public List<String> getCloseIslands() {
        return closeIslands;
    }

    public void setCloseIslands(List<IslandEntity> islandEntity) {
        for(IslandEntity island : islandEntity) {
            this.closeIslands.add(island.getId());
        }
    }
}
