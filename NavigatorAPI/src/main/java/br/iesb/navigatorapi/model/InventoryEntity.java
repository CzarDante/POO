package br.iesb.navigatorapi.model;

import java.util.ArrayList;
import java.util.List;

public class InventoryEntity {
    private int size;
    private List<ItemEntity> items = new ArrayList<>();

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

}
