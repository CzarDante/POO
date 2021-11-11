package br.iesb.navigatorapi.service.Market;

import br.iesb.navigatorapi.dto.MarketDTO;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.MarketEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.repository.MarketRepository;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class MarketService {

    @Autowired
    public MarketRepository marketRepository;

    @Autowired
    public ItemService itemService;

    @Autowired
    public InventoryService inventoryService;

    @Autowired
    public DTOEntityConversions dtoEntityConversions;

    public MarketService(){
        this.marketRepository = new MarketRepository();
    }

    public List<MarketEntity> getInstance(){
        return this.marketRepository.getMarketItems();
    }

    public List<MarketEntity> findOffers(ItemEntity.ItemID desiredResource) {
        List<MarketEntity> foundOffers = new ArrayList<MarketEntity>();

        for(MarketEntity itemAtMarket : marketRepository.getMarketItems()) {
            if(itemAtMarket.getOfferedItem().getResource() == desiredResource) {
                foundOffers.add(itemAtMarket);
            }
        }

        return foundOffers;
    }

    public MarketEntity findOffer(String tradeID) {

        for(MarketEntity offer : this.marketRepository.getMarketItems()) {
            if(offer.getTradeID().equals(tradeID)) {
                return offer;
            }
        }

        return null;
    }

    public MarketEntity postOffer(MarketEntity desiredOffer, UserEntity seller){

        boolean foundItem = false;

        for(ItemEntity itemAtSellerInventory : seller.getInventory().getItems()){

            if(itemAtSellerInventory.getResource() == desiredOffer.getOfferedItem().getResource()){
                foundItem = true;

                boolean hasEnoughQuantity = itemService.isEnoughQuantity(desiredOffer.getOfferedItem(), itemAtSellerInventory);
                if(!hasEnoughQuantity){
                    return null;
                }

                itemService.subtractQuantity(desiredOffer.getOfferedItem().getQuantity(), itemAtSellerInventory);
                if(itemAtSellerInventory.getQuantity() <= 0) {
                    inventoryService.removeItem(itemAtSellerInventory.getResource(), seller.getInventory());
                }

                break;
            }
        }

        if(!foundItem) {
            return null;
        }

        MarketEntity itemOffer = new MarketEntity();

        String tradeID = UUID.randomUUID().toString();
        itemOffer.setTradeID(tradeID);

        itemOffer.setSellerID(desiredOffer.getSellerID());
        itemOffer.setOfferedItem(desiredOffer.getOfferedItem());
        itemOffer.setPricePerItem(desiredOffer.getPricePerItem());


        this.marketRepository.setMarketItems(itemOffer);

        return itemOffer;
    }

    public boolean removeOffer(String offerID) {
        MarketEntity offer = findOffer(offerID);
        if(offer == null) {
            return false;
        }
        marketRepository.getMarketItems().remove(offer);
        return true;
    }
}
