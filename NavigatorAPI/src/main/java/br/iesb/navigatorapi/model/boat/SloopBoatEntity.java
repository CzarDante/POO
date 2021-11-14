package br.iesb.navigatorapi.model.boat;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;

import java.util.UUID;

public class SloopBoatEntity extends BoatEntity{

    public SloopBoatEntity() {

        // Cargo:
        InventoryEntity cargo = InventoryService.createInventory(1);

        // Required to craft:
        InventoryEntity requiredToCraft = InventoryService.createInventory(1);
        ItemEntity requiredItem;
        requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 500);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);

        String token = UUID.randomUUID().toString();
        setId(token);
        setCargo(cargo);
        setHealth(100);
        setType(boatID.sloop);
        setRequiredToCraft(requiredToCraft);
        setMaxTravelDistance(250);
        setTotalDistanceTravaled(0);

    }


}
