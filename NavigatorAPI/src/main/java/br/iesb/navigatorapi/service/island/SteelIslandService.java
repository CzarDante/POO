package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.island.IronIslandEntity;
import br.iesb.navigatorapi.model.island.SteelIslandEntity;
import br.iesb.navigatorapi.repository.IslandsRepository;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
public class SteelIslandService {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    ItemService itemService;
    @Autowired
    IslandsRepository islandsRepository;

    public SteelIslandEntity createSteelIsland() {

        Random random = new Random();

        SteelIslandEntity newSteelIsland = new SteelIslandEntity();

        InventoryEntity avaibleResources = inventoryService.createInventory(2);
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

    public SteelIslandEntity getIronIsland(String idToSearch) {

        List<SteelIslandEntity> steelIslandEntities = new ArrayList<>();
        steelIslandEntities = islandsRepository.getSteelIslandsInMemory();
        for(SteelIslandEntity island : steelIslandEntities) {
            if(idToSearch == island.getId())
                return island;
        }

        return null;
    }

    public InventoryEntity gatherSteelIslandResources (int timeSpent, SteelIslandEntity island) {

        int resourcesGatheredQuantity = timeSpent * 2;
        InventoryEntity gatheredResources = inventoryService.createInventory(2);

        for(ItemEntity itemAtIsland : island.getAvaibleResources().getItems()) {

            // Esse if é totalmente desnecessário, só não consigo remover itens usando esse for loop sem quebrar
            // o programa, e ainda não tô conseguindo usar o iterator para isso, então por enquanto não tô removendo
            // nenhum item.
            if(itemAtIsland.getQuantity() > 0) {

                ItemEntity itemGathered;
                itemGathered = itemService.createItem(itemAtIsland.getResource(), resourcesGatheredQuantity);

                // Se pegamos mais itens que os disponíveis na ilha
                if (itemGathered.getQuantity() >= itemAtIsland.getQuantity()) {
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
        }

        return gatheredResources;
    }
}
