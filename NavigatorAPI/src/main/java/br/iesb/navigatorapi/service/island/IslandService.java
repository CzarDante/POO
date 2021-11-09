package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.model.island.CarbonFiberIslandEntity;
import br.iesb.navigatorapi.model.island.CopperIslandEntity;
import br.iesb.navigatorapi.model.island.IronIslandEntity;
import br.iesb.navigatorapi.model.island.SteelIslandEntity;
import br.iesb.navigatorapi.repository.IslandsRepository;
import br.iesb.navigatorapi.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

@Service
public class IslandService {

    @Autowired
    CarbonFiberIslandService carbonFiberIslandService;
    @Autowired
    CopperIslandService copperIslandService;
    @Autowired
    SteelIslandService steelIslandService;
    @Autowired
    IronIslandService ironIslandService;
    @Autowired
    IslandsRepository islandsRepository;
    @Autowired
    InventoryService inventoryService;

    Random random = new Random();

    public enum islandTypes {
        carbonFiber,
        copper,
        iron,
        steel;
    }

    public String createIsland() {
        switch (islandTypes.values()[random.nextInt(islandTypes.values().length)]) {
            case carbonFiber:
                CarbonFiberIslandEntity carbonFiberIslandEntity;
                carbonFiberIslandEntity = carbonFiberIslandService.createCarbonFiberIsland();
                islandsRepository.setCarbonFiberIslandsInMemory(carbonFiberIslandEntity);
                return carbonFiberIslandEntity.getId();
            case copper:
                CopperIslandEntity copperIslandEntity;
                copperIslandEntity = copperIslandService.createCopperIsland();
                islandsRepository.setCopperIslandsInMemory(copperIslandEntity);
                return copperIslandEntity.getId();
            case iron:
                IronIslandEntity ironIslandEntity;
                ironIslandEntity = ironIslandService.createIronIsland();
                islandsRepository.setIronIslandsInMemory(ironIslandEntity);
                return ironIslandEntity.getId();
            case steel:
                SteelIslandEntity steelIslandEntity;
                steelIslandEntity = steelIslandService.createSteelIsland();
                islandsRepository.setSteelIslandsInMemory(steelIslandEntity);
                return steelIslandEntity.getId();
        }

        return null;
    }

    public boolean gatherResource(UserEntity player, Integer time) {


        islandTypes currentIslandType = getIslandType(player.getCurrentIslandID());
        if(currentIslandType == null) {
            return false;
        }

        if(player.getLootCooldown() - System.currentTimeMillis() > 0){
            return false;
        }else{
            player.setLootCooldown(time);
        }

        String currentIsland = player.getCurrentIslandID();
        InventoryEntity itemsGathered = inventoryService.createInventory(10);

        switch(currentIslandType) {
            case iron:
                IronIslandEntity ironIslandEntity = ironIslandService.getIronIsland(currentIsland);
                itemsGathered = ironIslandService.gatherIronIslandResources(time,ironIslandEntity);
                break;
            case steel:
                SteelIslandEntity steelIslandEntity = steelIslandService.getIronIsland(currentIsland);
                itemsGathered = steelIslandService.gatherSteelIslandResources(time, steelIslandEntity);
                break;
            case copper:
                CopperIslandEntity copperIslandEntity = copperIslandService.getCopperIsland(currentIsland);
                itemsGathered = copperIslandService.gatherCopperIslandResources(time, copperIslandEntity);
                break;
            case carbonFiber:
                CarbonFiberIslandEntity carbonFiberIslandEntity = carbonFiberIslandService.getCarbonFiberIsland(currentIsland);
                itemsGathered = carbonFiberIslandService.gatherCarbonFiberResources(time, carbonFiberIslandEntity);
                break;
            default:
                return false;
        }

        // Vendo se ocorreu alguma mudança no inventário para dizer se coletou algo ou não
        // Meio preguiçoso fazer assim eu acho, se alguém tiver uma ideia melhor!
        InventoryEntity inventoryBeforeChanges = inventoryService.copyInventory(player.getInventory());
        inventoryService.addItemToInventory(itemsGathered, player.getInventory());

        if(inventoryService.isInventoriesEqual(inventoryBeforeChanges, player.getInventory()))
            return false;

        return true;
    }

    public islandTypes getIslandType(String idToSearch) {

        List<CarbonFiberIslandEntity> carbonFiberIslandEntities = new ArrayList<>();
        carbonFiberIslandEntities = islandsRepository.getCarbonFiberIslandsInMemory();
        for(CarbonFiberIslandEntity island : carbonFiberIslandEntities) {
            if(idToSearch == island.getId())
                return islandTypes.carbonFiber;
        }

        List<CopperIslandEntity> copperIslandEntities = new ArrayList<>();
        copperIslandEntities = islandsRepository.getCopperIslandsInMemory();
        for(CopperIslandEntity island : copperIslandEntities) {
            if(idToSearch == island.getId())
                return islandTypes.copper;
        }

        List<IronIslandEntity> ironIslandEntities = new ArrayList<>();
        ironIslandEntities = islandsRepository.getIronIslandsInMemory();
        for(IronIslandEntity island : ironIslandEntities) {
            if(idToSearch == island.getId())
                return islandTypes.iron;
        }

        List<SteelIslandEntity> steelIslandEntities = new ArrayList<>();
        steelIslandEntities = islandsRepository.getSteelIslandsInMemory();
        for(SteelIslandEntity island : steelIslandEntities) {
            if(idToSearch == island.getId())
                return islandTypes.steel;
        }

        return null;
    }

}
