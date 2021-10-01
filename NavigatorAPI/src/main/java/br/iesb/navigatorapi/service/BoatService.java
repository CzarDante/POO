package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatService {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;

    public BoatEntity createBoat(BoatEntity.boatID type, String id) {
        BoatEntity newBoat = new BoatEntity();
        InventoryEntity requiredToCraft = new InventoryEntity();

        InventoryEntity cargo = new InventoryEntity();

        //Definindo os itens necessários para criar o barco
        ItemEntity requiredItem;
        switch(type) {
            case sloop:
                requiredToCraft = inventoryService.createInventory(2);
                requiredItem = itemService.createItem(ItemEntity.ItemID.wood, 500);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);

                cargo.setSize(5);
                newBoat.setMaxDistance(100);

                break;
            case sailboat:
                requiredToCraft = inventoryService.createInventory(2);
                requiredItem = itemService.createItem(ItemEntity.ItemID.wood, 1000);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = itemService.createItem(ItemEntity.ItemID.iron, 200);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);

                cargo.setSize(10);
                newBoat.setMaxDistance(200);
                break;
            case brigantine:
                requiredToCraft = inventoryService.createInventory(2);
                requiredItem = itemService.createItem(ItemEntity.ItemID.wood, 1500);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = itemService.createItem(ItemEntity.ItemID.copper, 200);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                cargo.setSize(15);
                newBoat.setMaxDistance(300);
                break;
            case galleon:
                requiredToCraft = inventoryService.createInventory(5);
                requiredItem = itemService.createItem(ItemEntity.ItemID.wood, 2000);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = itemService.createItem(ItemEntity.ItemID.steel, 200);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = itemService.createItem(ItemEntity.ItemID.iron, 200);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = itemService.createItem(ItemEntity.ItemID.copper, 200);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = itemService.createItem(ItemEntity.ItemID.carbonFiber, 200);
                requiredToCraft = inventoryService.addItemToInventory(requiredItem, requiredToCraft);

                cargo.setSize(20);
                newBoat.setMaxDistance(400);
                break;
        }

        newBoat.setType(type);
        newBoat.setCargo(cargo);
        newBoat.setRequiredToCraft(requiredToCraft);
        newBoat.setId(id);

        return newBoat;
    }

}
