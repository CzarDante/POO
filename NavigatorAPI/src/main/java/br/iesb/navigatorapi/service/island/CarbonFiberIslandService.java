package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.island.CarbonFiberIslandEntity;
import br.iesb.navigatorapi.model.island.CopperIslandEntity;
import br.iesb.navigatorapi.repository.IslandsRepository;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class CarbonFiberIslandService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;
    @Autowired
    IslandsRepository islandsRepository;

    public CarbonFiberIslandEntity createCarbonFiberIsland() {

        Random random = new Random();
        CarbonFiberIslandEntity newCarbonFiberIsland = new CarbonFiberIslandEntity();

        InventoryEntity avaibleResources = inventoryService.createInventory(2);
        ItemEntity newItem;
        newItem = itemService.createItem(ItemEntity.ItemID.wood, random.nextInt(500 - 100) + 100);
        inventoryService.addItemToInventory(newItem, avaibleResources);
        newItem = itemService.createItem(ItemEntity.ItemID.carbonFiber, random.nextInt(100 - 25) + 25);
        inventoryService.addItemToInventory(newItem, avaibleResources);


        String token = UUID.randomUUID().toString();

        newCarbonFiberIsland.setId(token);
        newCarbonFiberIsland.setAvaibleResources(avaibleResources);
        newCarbonFiberIsland.setDistance(random.nextInt(300));
        newCarbonFiberIsland.setSize(random.nextInt(1000 - 250) + 250);

        return newCarbonFiberIsland;

    }

    public CarbonFiberIslandEntity getCarbonFiberIsland(String idToSearch) {

        List<CarbonFiberIslandEntity> carbonFiberIslandEntities = new ArrayList<>();
        carbonFiberIslandEntities = islandsRepository.getCarbonFiberIslandsInMemory();
        for(CarbonFiberIslandEntity island : carbonFiberIslandEntities) {
            if(idToSearch == island.getId())
                return island;
        }

        return null;
    }

    public InventoryEntity gatherCarbonFiberResources (int timeSpent, CarbonFiberIslandEntity island) {

        int resourcesGatheredQuantity = timeSpent * 2;
        InventoryEntity gatheredResources = inventoryService.createInventory(2);

        for(ItemEntity itemAtIsland : island.getAvaibleResources().getItems()) {

            // Esse if é totalmente desnecessário, só não consigo remover itens usando esse for loop sem quebrar
            // o programa, e ainda não tô conseguindo usar o iterator para isso, então por enquanto não tô removendo
            // nenhum item.
            if(itemAtIsland.getQuantity() <= 0)
                break;

            ItemEntity itemGathered;
            itemGathered = itemService.createItem(itemAtIsland.getResource(), resourcesGatheredQuantity);

            // Se pegamos mais itens que os disponíveis na ilha
            if(itemGathered.getQuantity() >= itemAtIsland.getQuantity()) {
                itemGathered.setQuantity(itemAtIsland.getQuantity());

                // Remoção que não dá para fazer sem quebrar o código dentro do loop
                //inventoryService.removeItem(itemAtIsland.getResource(), island.getAvaibleResources());

                // Subtração que seria desnecessária se fosse possível remover item sem quebrar
                inventoryService.subtractFromInventory(itemGathered, island.getAvaibleResources());
            } else {
                inventoryService.subtractFromInventory(itemGathered, island.getAvaibleResources());
            }
            inventoryService.addItemToInventory(itemGathered, gatheredResources);
        }

        return gatheredResources;
    }

}
