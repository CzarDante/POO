package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.island.SteelIslandEntity;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.UUID;

@Controller
public class SteelIslandService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;

    public SteelIslandEntity createSteelIslandService () {

        Random random = new Random();

        SteelIslandEntity newSteelIsland = new SteelIslandEntity();

        InventoryEntity avaibleResources = new InventoryEntity();
        ItemEntity newItem;
        newItem = itemService.createItem(ItemEntity.ItemID.wood, random.nextInt(1000 - 500) + 500);
        inventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = itemService.createItem(ItemEntity.ItemID.steel, random.nextInt(100 - 25) + 25);
        inventoryService.addItemToInventory(newItem, avaibleResources);


        String token = UUID.randomUUID().toString();

        newSteelIsland.setId(token);
        newSteelIsland.setAvaibleResources(avaibleResources);
        newSteelIsland.setDistance(random.nextInt(300));
        newSteelIsland.setSize(random.nextInt(1000 - 250) + 250);

        return newSteelIsland;

    }
}
