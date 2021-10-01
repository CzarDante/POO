package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.island.CarbonFiberIslandEntity;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.UUID;

@Controller
public class CarbonFiberIslandService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;

    public CarbonFiberIslandEntity createCarbonFiberIslandService () {

        Random random = new Random();
        CarbonFiberIslandEntity newCarbonFiberIsland = new CarbonFiberIslandEntity();

        InventoryEntity avaibleResources = new InventoryEntity();
        ItemEntity newItem = new ItemEntity();
        newItem = itemService.createItem(ItemEntity.ItemID.wood, random.nextInt(500 - 100) + 100);
        avaibleResources = inventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = itemService.createItem(ItemEntity.ItemID.carbonFiber, random.nextInt(100 - 25) + 25);
        avaibleResources = inventoryService.addItemToInventory(newItem, avaibleResources);


        String token = UUID.randomUUID().toString();

        newCarbonFiberIsland.setId(token);
        newCarbonFiberIsland.setAvaibleResources(avaibleResources);
        newCarbonFiberIsland.setDistance(random.nextInt(300));
        newCarbonFiberIsland.setSize(random.nextInt(1000 - 250) + 250);

        return newCarbonFiberIsland;

    }

    public InventoryEntity gatherResources (int timeSpent, CarbonFiberIslandEntity island) {

        InventoryEntity gatheredResources = new InventoryEntity();

        InventoryEntity islandResources = new InventoryEntity();
        islandResources = island.getAvaibleResources();

        int resourcesGatheredQuantity = timeSpent * 2;

        ItemEntity carbonFiberFound = new ItemEntity();
        carbonFiberFound = itemService.createItem(ItemEntity.ItemID.carbonFiber, resourcesGatheredQuantity);

        ItemEntity carbonAtIsland = new ItemEntity();
        carbonAtIsland = inventoryService.getItemInInventory(ItemEntity.ItemID.carbonFiber, islandResources);
        if(carbonAtIsland != null) {

            // Se pegamos mais itens que os disponíveis na ilha
            if(carbonFiberFound.getQuantity() >= carbonAtIsland.getQuantity()) {

                carbonFiberFound.setQuantity(carbonAtIsland.getQuantity());
                islandResources = inventoryService.removeItem(ItemEntity.ItemID.carbonFiber, islandResources);

            } else {

                islandResources = inventoryService.subtractFromInventory(carbonFiberFound, islandResources);

            }

            gatheredResources = inventoryService.addItemToInventory(carbonFiberFound, gatheredResources);

        }

        ItemEntity woodFound = new ItemEntity();
        woodFound = itemService.createItem(ItemEntity.ItemID.wood, resourcesGatheredQuantity);

        ItemEntity woodAtIsland = new ItemEntity();
        woodAtIsland = inventoryService.getItemInInventory(ItemEntity.ItemID.wood, islandResources);
        if(woodAtIsland != null) {

            // Se pegamos mais itens que os disponíveis na ilha
            if(carbonFiberFound.getQuantity() >= woodAtIsland.getQuantity()) {

                carbonFiberFound.setQuantity(woodAtIsland.getQuantity());
                islandResources = inventoryService.removeItem(ItemEntity.ItemID.wood, islandResources);



            } else {

                islandResources = inventoryService.subtractFromInventory(carbonFiberFound, islandResources);

            }

            gatheredResources = inventoryService.addItemToInventory(carbonFiberFound, gatheredResources);

        }

        return gatheredResources;
    }

}
