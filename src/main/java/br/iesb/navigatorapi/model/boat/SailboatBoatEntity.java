package br.iesb.navigatorapi.model.boat;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;

import java.util.UUID;

public class SailboatBoatEntity extends BoatEntity{

    public SailboatBoatEntity() {

        // Cargo:
        InventoryEntity cargo = InventoryService.createInventory(2);

        // Required to craft:
        InventoryEntity requiredToCraft = InventoryService.createInventory(2);
        ItemEntity requiredItem;
        requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 1000);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.iron, 250);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);


        String token = UUID.randomUUID().toString();
        setId(token);
        setCargo(cargo);
        setHealth(125);
        setType(boatID.sailboat);
        setRequiredToCraft(requiredToCraft);
        setMaxTravelDistance(500);
        setTotalDistanceTravaled(0);
    }

}
