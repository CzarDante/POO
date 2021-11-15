package br.iesb.navigatorapi.service.boat;

import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;
import org.springframework.stereotype.Service;

@Service
public class BoatService {

    public BoatEntity createBoat(BoatEntity.boatID type, String id) {
        BoatEntity newBoat = new BoatEntity();
        InventoryEntity requiredToCraft = new InventoryEntity();

        InventoryEntity cargo = new InventoryEntity();

        //Definindo os itens necessários para criar o barco
        ItemEntity requiredItem;
        switch(type) {
            case sloop:
                requiredToCraft = InventoryService.createInventory(2);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 500);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);

                cargo.setSize(5);
                newBoat.setMaxTravelDistance(100);

                break;
            case sailboat:
                requiredToCraft = InventoryService.createInventory(2);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 1000);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.iron, 200);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);

                cargo.setSize(10);
                newBoat.setMaxTravelDistance(200);
                break;
            case brigantine:
                requiredToCraft = InventoryService.createInventory(2);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 1500);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.copper, 200);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                cargo.setSize(15);
                newBoat.setMaxTravelDistance(300);
                break;
            case galleon:
                requiredToCraft = InventoryService.createInventory(5);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.wood, 2000);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.steel, 200);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.iron, 200);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.copper, 200);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);
                requiredItem = ItemService.createItem(ItemEntity.ItemID.carbonFiber, 200);
                InventoryService.addItemToInventory(requiredItem, requiredToCraft);

                cargo.setSize(20);
                newBoat.setMaxTravelDistance(400);
                break;
        }

        newBoat.setType(type);
        newBoat.setCargo(cargo);
        newBoat.setRequiredToCraft(requiredToCraft);
        newBoat.setId(id);

        return newBoat;
    }

    public String getRequirementToCraft(BoatEntity boat) {

        String formattedString = boat.getType().toString();

        for(ItemEntity item : boat.getRequiredToCraft().getItems()) {
            formattedString += " | ";
            formattedString += item.getResource();
            formattedString += " x";
            formattedString += item.getQuantity();
        }

        return formattedString;
    }

}
