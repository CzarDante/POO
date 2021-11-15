package br.iesb.navigatorapi.service.player;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.model.island.*;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;
import org.apache.catalina.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Scope("singleton")
public class PlayerService {

    Random random = new Random();

    public UserEntity createPlayer(String token, String name, int inventorySize) {
        UserEntity newPlayer = new UserEntity();

        newPlayer.setToken(token);
        newPlayer.setName(name);
        newPlayer.setForageSpeed(2);

        InventoryEntity newInventory = new InventoryEntity();
        newInventory.setSize(inventorySize);
        //Criando itens hardcoded para testar o resto das coisas
        ItemEntity newItem;
        for(ItemEntity.ItemID itemID : ItemEntity.ItemID.values()) {
            newItem = ItemService.createItem(itemID, 10000);
            InventoryService.addItemToInventory(newItem, newInventory);
        }
        newPlayer.setInventory(newInventory);

        newPlayer.setCurrentIsland(createRandomIsland());
        newPlayer.getCurrentIsland().setDistance(0);

        for(int i = 0; i < random.nextInt(5 - 3) + 3; i++) {
            newPlayer.setCloseIslands(createRandomIsland());
        }

        return newPlayer;
    }

    private IslandEntity createRandomIsland() {

        switch (IslandEntity.IslandType.values()[random.nextInt(IslandEntity.IslandType.values().length)]) {
            case carbonFiber:
                CarbonFiberIslandEntity carbonFiberIslandEntity = new CarbonFiberIslandEntity();
                return carbonFiberIslandEntity;
            case copper:
                CopperIslandEntity copperIslandEntity = new CopperIslandEntity();
                return copperIslandEntity;
            case iron:
                IronIslandEntity ironIslandEntity = new IronIslandEntity();
                return ironIslandEntity;
            case steel:
                SteelIslandEntity steelIslandEntity = new SteelIslandEntity();
                return steelIslandEntity;
        }

        return null;
    }

    public static void removePlayerCloseIslands(UserEntity player) {
        List<IslandEntity> islands = player.getCloseIslands();
        if(islands.size() > 0) {
            islands.remove(0);
            removePlayerCloseIslands(player);
        }
    }

    public static void createRandomCloseIslands(UserEntity player) {
        Random random = new Random();
        PlayerService playerService = new PlayerService();

        for(int i = 0; i < random.nextInt(5 - 3) + 3; i++) {
            player.setCloseIslands(playerService.createRandomIsland());
        }
    }
    public boolean craftBoat(BoatEntity desiredBoat, UserEntity player) {

        InventoryEntity playerInventory = player.getInventory();
        InventoryEntity requiredToCraft = desiredBoat.getRequiredToCraft();

        if(InventoryService.isItemsContained(desiredBoat.getRequiredToCraft(), playerInventory)) {
            InventoryService.subtractFromInventory(requiredToCraft, playerInventory);
            player.getBoats().add(desiredBoat);
            return true;
        } else {
            return false;
        }

    }

    public static boolean isGatheringInCooldown(UserEntity player) {
        if(player.getLootCooldown() - System.currentTimeMillis() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
