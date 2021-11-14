package br.iesb.navigatorapi.model.island;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;

import java.util.UUID;

public class IronIslandEntity extends IslandEntity{

    public IronIslandEntity() {

        InventoryEntity avaibleResources = InventoryService.createInventory(2);

        ItemEntity newItem;

        newItem = ItemService.createItem(ItemEntity.ItemID.wood, random.nextInt(1000 - 500) + 500);
        InventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = ItemService.createItem(ItemEntity.ItemID.iron, random.nextInt(100 - 25) + 25);
        InventoryService.addItemToInventory(newItem, avaibleResources);

        setAvaibleResources(avaibleResources);

        String token = UUID.randomUUID().toString();
        setId(token);
        setDistance(random.nextInt(300));
        setSize(random.nextInt(1000 - 250) + 250);
        setIslandType(IslandType.iron);
    }


}
