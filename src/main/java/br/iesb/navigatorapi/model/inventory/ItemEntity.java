package br.iesb.navigatorapi.model.inventory;

public class ItemEntity {

    private ItemID resource;
    private int quantity;

    public enum ItemID{
        wood,
        copper,
        iron,
        steel,
        carbonFiber,
        money;
    }

    public ItemID getResource() {
        return resource;
    }

    public void setResource(ItemID resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
