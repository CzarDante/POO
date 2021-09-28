package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.island.IronIslandEntity;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.UUID;

@Controller
public class IronIslandService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;

    public IronIslandEntity createIronIslandService () {

        Random random = new Random();
        IronIslandEntity newIronIsland = new IronIslandEntity();

        InventoryEntity avaibleResources = new InventoryEntity();
        ItemEntity newItem = new ItemEntity();
        newItem = itemService.createItem(ItemEntity.ItemID.wood, random.nextInt(1000 - 500) + 500);
        avaibleResources = inventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = itemService.createItem(ItemEntity.ItemID.iron, random.nextInt(100 - 25) + 25);
        avaibleResources = inventoryService.addItemToInventory(newItem, avaibleResources);


        String token = UUID.randomUUID().toString();

        newIronIsland.setId(token);
        newIronIsland.setAvaibleResources(avaibleResources);
        newIronIsland.setDistance(random.nextInt(300));
        newIronIsland.setSize(random.nextInt(1000 - 250) + 250);

        return newIronIsland;
    }
}
