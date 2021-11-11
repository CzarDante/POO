package br.iesb.navigatorapi.dto;

import br.iesb.navigatorapi.model.ItemEntity;

public class MarketDTO {
    private String tradeID;
    private ItemEntity offeredItem;
    private ItemEntity pricePerItem;

    public ItemEntity getOfferedItem() {
        return offeredItem;
    }

    public void setOfferedItem(ItemEntity offeredItem) {
        this.offeredItem = offeredItem;
    }

    public ItemEntity getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(ItemEntity pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public String getTradeID() {
        return tradeID;
    }

    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }
}
