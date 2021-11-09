package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.MerchandiseEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Scope("singleton")
public class MarketService {

    @Autowired
    public MarketRepository marketRepository;

    @Autowired
    public ItemService itemService;

    public MarketService(){
        this.marketRepository = new MarketRepository();
    }

    public List<MerchandiseEntity> getInstance(){
        return this.marketRepository.getMarketItems();
    }

    public MerchandiseEntity postOffer(MerchandiseEntity item, UserEntity player){
        MerchandiseEntity itemOffer = new MerchandiseEntity();

        for(ItemEntity findItem : player.getInventory().getItems()){
            if(findItem.getResource().toString() == item.getOfferedItem().getResource().toString()){
                boolean haveQuantity = itemService.isEnoughQuantity(item.getOfferedItem(), findItem);
                if(!haveQuantity){
                    return null;
                }
                itemService.subtractQuantity(item.getOfferedItem().getQuantity(), findItem);
            }
        }

        itemOffer.setPlayerOffer(item.getPlayerOffer());
        itemOffer.setOfferedItem(item.getOfferedItem());
        itemOffer.setDemandItem(item.getDemandItem());

        this.marketRepository.setMarketItems(itemOffer);

        return item;
    }
}
