package br.iesb.navigatorapi.model;

public class BoatEntity {
    private String id;
    private int size;
    private int maxDistance;
    private int maxLoad;
    private int totalDistanceTravaled;

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

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getTotalDistanceTravaled() {
        return totalDistanceTravaled;
    }

    public void setTotalDistanceTravaled(int totalDistanceTravaled) {
        this.totalDistanceTravaled = totalDistanceTravaled;
    }
}
