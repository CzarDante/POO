package br.iesb.navigatorapi.dto;

import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.island.IslandEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String name;
    private InventoryEntity inventory = new InventoryEntity();
    private String currentIsland;
    private List<String> closeIslands = new ArrayList<String>();

    private List<BoatDTO> boats = new ArrayList<BoatDTO>();

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


    public List<BoatDTO> getBoats() {
        return boats;
    }

    public void setBoats(BoatDTO boat) {
        this.boats.add(boat);
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
