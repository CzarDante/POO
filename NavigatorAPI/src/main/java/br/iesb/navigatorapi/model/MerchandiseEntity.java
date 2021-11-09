package br.iesb.navigatorapi.model;

public class MerchandiseEntity {
    private String playerOffer;
    private ItemEntity offeredItem;
    private ItemEntity demandItem;

    public String getPlayerOffer() {
        return playerOffer;
    }

    public void setPlayerOffer(String playerOffer) {
        this.playerOffer = playerOffer;
    }

    public ItemEntity getOfferedItem() {
        return offeredItem;
    }

    public void setOfferedItem(ItemEntity offeredItem) {
        this.offeredItem = offeredItem;
    }

    public ItemEntity getDemandItem() {
        return demandItem;
    }

    public void setDemandItem(ItemEntity demandItem) {
        this.demandItem = demandItem;
    }
}
