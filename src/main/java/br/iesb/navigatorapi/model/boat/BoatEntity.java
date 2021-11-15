package br.iesb.navigatorapi.model.boat;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;

public abstract class BoatEntity {

    private String id;
    private boatID type;

    private int health;
    private int maxTravelDistance;
    private int totalDistanceTravaled;

    private InventoryEntity cargo;
    private InventoryEntity requiredToCraft;

    public enum boatID {
        sloop,
        sailboat,
        brigantine,
        galleon;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxTravelDistance() {
        return maxTravelDistance;
    }

    public void setMaxTravelDistance(int maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public int getTotalDistanceTravaled() {
        return totalDistanceTravaled;
    }

    public void setTotalDistanceTravaled(int totalDistanceTravaled) {
        this.totalDistanceTravaled = totalDistanceTravaled;
    }

    public InventoryEntity getCargo() {
        return cargo;
    }

    public void setCargo(InventoryEntity cargo) {
        this.cargo = cargo;
    }

    public InventoryEntity getRequiredToCraft() {
        return requiredToCraft;
    }

    public void setRequiredToCraft(InventoryEntity requiredToCraft) {
        this.requiredToCraft = requiredToCraft;
    }

    public boatID getType() {
        return type;
    }

    public void setType(boatID type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
