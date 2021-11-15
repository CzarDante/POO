package br.iesb.navigatorapi.model.player;

//import java.util.ArrayList;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.island.IslandEntity;

import java.util.ArrayList;
import java.util.List;

public class UserEntity {
    private String name;
    private String token;
    private InventoryEntity inventory = new InventoryEntity();
    private List<BoatEntity> boats = new ArrayList<BoatEntity>();
    private long lootCooldown;

    private int forageSpeed;

    private IslandEntity currentIsland;
    private List<IslandEntity> closeIslands = new ArrayList<IslandEntity>();

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

    public List<BoatEntity> getBoats() {
        return boats;
    }

    public void setBoats(BoatEntity boatEntity) {
        this.boats.add(boatEntity);
    }

    public IslandEntity getCurrentIsland() {
        return currentIsland;
    }

    public void setCurrentIsland(IslandEntity currentIsland) {
        this.currentIsland = currentIsland;
    }

    public List<IslandEntity> getCloseIslands() {
        return closeIslands;
    }

    public void setCloseIslands(IslandEntity islandEntity) {
        closeIslands.add(islandEntity);
    }

    public long getLootCooldown() {
        return lootCooldown;
    }

    public void setLootCooldown(int time) {
        this.lootCooldown = System.currentTimeMillis() + time * 60000;
    }

    public int getForageSpeed() {
        return forageSpeed;
    }

    public void setForageSpeed(int forageSpeed) {
        this.forageSpeed = forageSpeed;
    }
}
