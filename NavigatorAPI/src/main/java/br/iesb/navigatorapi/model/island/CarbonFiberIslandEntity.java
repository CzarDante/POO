package br.iesb.navigatorapi.model.island;

import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;

import java.util.UUID;

public class CarbonFiberIslandEntity extends IslandEntity{

    public CarbonFiberIslandEntity() {

        avaibleResources = InventoryService.createInventory(2);

        ItemEntity newItem;

        newItem = ItemService.createItem(ItemEntity.ItemID.wood, random.nextInt(1000 - 500) + 500);
        InventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = ItemService.createItem(ItemEntity.ItemID.carbonFiber, random.nextInt(100 - 25) + 25);
        InventoryService.addItemToInventory(newItem, avaibleResources);

        String token = UUID.randomUUID().toString();

        this.id = token;
        this.distance = random.nextInt(300);
        this.size = random.nextInt(1000 - 250) + 250;
        this.islandType = IslandType.carbonFiber;
    }

}
