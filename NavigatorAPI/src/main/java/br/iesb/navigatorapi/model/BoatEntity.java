package br.iesb.navigatorapi.model;

public class BoatEntity {

    private String id;
    private boatID type;
    private int size;
    private int maxDistance;
    private int totalDistanceTravaled;
    private InventoryEntity cargo = new InventoryEntity();
    private InventoryEntity requiredToCraft = new InventoryEntity();

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
}
