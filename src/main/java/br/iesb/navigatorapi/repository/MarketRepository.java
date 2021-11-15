package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.market.MarketEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class MarketRepository {
    private List<MarketEntity> marketItems = new ArrayList<>();

    public List<MarketEntity> getMarketItems() {
        return marketItems;
    }

    public void setMarketItems(MarketEntity marketItem) {
        this.marketItems.add(marketItem);
    }

    public void removeMarketItems(MarketEntity marketItem) {
        this.marketItems.remove(marketItem);
    }
}
