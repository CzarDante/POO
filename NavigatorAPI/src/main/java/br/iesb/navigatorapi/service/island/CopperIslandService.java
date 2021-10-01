package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.island.CopperIslandEntity;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.UUID;


@Service
public class CopperIslandService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;

    public CopperIslandEntity createCopperIslandService () {

        Random random = new Random();
        CopperIslandEntity newCopperIsland = new CopperIslandEntity();

        InventoryEntity avaibleResources = new InventoryEntity();
        ItemEntity newItem = new ItemEntity();
        newItem = itemService.createItem(ItemEntity.ItemID.wood, random.nextInt(1000 - 500) + 500);
        avaibleResources = inventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = itemService.createItem(ItemEntity.ItemID.copper, random.nextInt(100 - 25) + 25);
        avaibleResources = inventoryService.addItemToInventory(newItem, avaibleResources);


        String token = UUID.randomUUID().toString();

        newCopperIsland.setId(token);
        newCopperIsland.setAvaibleResources(avaibleResources);
        newCopperIsland.setDistance(random.nextInt(300 - 0) + 0);
        newCopperIsland.setSize(random.nextInt(1000 - 250) + 250);

        return newCopperIsland;



    }
}
