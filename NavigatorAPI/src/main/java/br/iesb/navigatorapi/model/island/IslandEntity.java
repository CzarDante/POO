package br.iesb.navigatorapi.model.island;

import br.iesb.navigatorapi.model.InventoryEntity;

public class IslandEntity {

    private String id;
    private int size;
    private int distance;

    private InventoryEntity avaibleResources = new InventoryEntity();

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
        return avaibleResources;
    }

    public void setAvaibleResources(InventoryEntity avaibleResources) {
        this.avaibleResources = avaibleResources;
    }
}
