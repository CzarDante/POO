package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.model.island.IslandEntity;
import br.iesb.navigatorapi.service.player.PlayerService;
import br.iesb.navigatorapi.service.market.TradingService;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public abstract class IslandService {

    public static int gatherResource(UserEntity player, Integer time) {

        IslandEntity currentIsland = player.getCurrentIsland();
        if(isIslandEmpty(currentIsland)) {
            // Erro: A ilha está vazia (Não há mais recursos a serem obtidos)
            return 1;
        }

        if(PlayerService.isGatheringInCooldown(player)) {
            // Erro: Gathering ainda está em cooldown
            return 2;
        } else {
            player.setLootCooldown(time);
        }

        int amountOfResourcesGathered = time * player.getForageSpeed();
        int amountOfItemsInIsland = player.getCurrentIsland().getAvaibleResources().getItems().size();

        Iterator<ItemEntity> itemEntityIterator = currentIsland.getAvaibleResources().getItems().iterator();
        while(itemEntityIterator.hasNext()) {
            TradingService.forceTransferItem(player.getInventory(), currentIsland.getAvaibleResources(), itemEntityIterator.next().getResource(), amountOfResourcesGathered);
        }

        return 0;
    }

    private static boolean isIslandEmpty(IslandEntity island) {
        boolean isEmpty = true;

        for(ItemEntity itemsAtIsland : island.getAvaibleResources().getItems()) {
            if(itemsAtIsland.getQuantity() > 0) {
                isEmpty = false;
            }
        }

        return isEmpty;
    }
}
