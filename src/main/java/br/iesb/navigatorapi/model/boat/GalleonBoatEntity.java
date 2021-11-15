package br.iesb.navigatorapi.model.boat;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;

import java.util.UUID;

public class GalleonBoatEntity extends BoatEntity{
    public GalleonBoatEntity () {
        // Cargo:
        InventoryEntity cargo = InventoryService.createInventory(3);

        // Required to craft:
        InventoryEntity requiredToCraft = InventoryService.createInventory(5);
        ItemEntity requiredItem;
        requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 4000);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.iron, 500);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.steel, 500);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.copper, 500);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);
        requiredItem = ItemService.createItem(ItemEntity.ItemID.carbonFiber, 750);
        InventoryService.addItemToInventory(requiredItem, requiredToCraft);


        String token = UUID.randomUUID().toString();
        setId(token);
        setCargo(cargo);
        setHealth(200);
        setType(boatID.galleon);
        setRequiredToCraft(requiredToCraft);
        setMaxTravelDistance(1000);
        setTotalDistanceTravaled(0);
    }
}
