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
        switch(type) {
            case sloop:
                requiredToCraft.setSize(2);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 10), requiredToCraft);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.metal, 10), requiredToCraft);
                cargo.setSize(5);
                newBoat.setMaxDistance(100);
                break;
            case sailboat:
                requiredToCraft.setSize(2);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 20), requiredToCraft);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.metal, 20), requiredToCraft);
                cargo.setSize(10);
                newBoat.setMaxDistance(200);
                break;
            case brigantine:
                requiredToCraft.setSize(2);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 30), requiredToCraft);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.metal, 30), requiredToCraft);
                cargo.setSize(15);
                newBoat.setMaxDistance(300);
                break;
            case galleon:
                requiredToCraft.setSize(2);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 40), requiredToCraft);
                requiredToCraft = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.metal, 40), requiredToCraft);
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
