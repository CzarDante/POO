package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.MerchandiseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class MarketRepository {
    private List<MerchandiseEntity> marketItems = new ArrayList<>();

    public List<MerchandiseEntity> getMarketItems() {
        return marketItems;
    }

    public void setMarketItems(MerchandiseEntity marketItem) {
        this.marketItems.add(marketItem);
    }

    public void removeMarketItems(MerchandiseEntity marketItem) {
        this.marketItems.remove(marketItem);
    }
}
