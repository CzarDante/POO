package br.iesb.navigatorapi.model.boat;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;

import java.util.UUID;

public class BrigantineBoatEntity extends BoatEntity{
    public BrigantineBoatEntity() {

        // Cargo:
        InventoryEntity cargo = InventoryService.createInventory(3);

        // Required to craft:
        InventoryEntity requiredToCraft = InventoryService.createInventory(3);
        ItemEntity requiredItem;
        requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 2000);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.iron, 250);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.steel, 250);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);


        String token = UUID.randomUUID().toString();
        setId(token);
        setCargo(cargo);
        setHealth(150);
        setType(boatID.brigantine);
        setRequiredToCraft(requiredToCraft);
        setMaxTravelDistance(750);
        setTotalDistanceTravaled(0);
    }


}
