package br.iesb.navigatorapi.model;

public class MarketEntity {
    private String sellerID;
    private String tradeID;
    private ItemEntity offeredItem;
    private ItemEntity pricePerItem;

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

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
