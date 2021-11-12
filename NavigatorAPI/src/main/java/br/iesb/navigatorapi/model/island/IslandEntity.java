package br.iesb.navigatorapi.model.island;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import java.util.Random;

public abstract class IslandEntity {

    protected String id;
    protected IslandType islandType;
    protected int size;
    protected int distance;

    protected InventoryEntity avaibleResources;

    public enum IslandType {
        carbonFiber,
        copper,
        iron,
        steel;
    }

    Random random = new Random();

    public IslandType getIslandType() {
        return islandType;
    }

    public void setIslandType(IslandType islandType) {
        this.islandType = islandType;
    }

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public InventoryEntity getAvaibleResources() {
        return this.avaibleResources;
    }

    public void setAvaibleResources(InventoryEntity avaibleResources) {
        this.avaibleResources = avaibleResources;
    }
}
