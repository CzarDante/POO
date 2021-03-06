package br.iesb.navigatorapi.dto;

import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.inventory.InventoryEntity;

public class BoatDTO {
    private String id;
    private BoatEntity.boatID type;
    private int health;
    private int size;
    private int maxDistance;
    private int totalDistanceTravaled;
    private InventoryEntity cargo = new InventoryEntity();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
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

    public BoatEntity.boatID getType() {
        return type;
    }

    public void setType(BoatEntity.boatID type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
