package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.island.IslandService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@Scope("singleton")
public class PlayerService {

    Random random = new Random();

    @Autowired
    IslandService islandService;
    @Autowired
    ItemService itemService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    DTOEntityConversions conversions;


    public UserEntity createPlayer(String token, String name, int inventorySize) {
        UserEntity newPlayer = new UserEntity();
        newPlayer.setToken(token);
        newPlayer.setName(name);

        InventoryEntity newInventory = new InventoryEntity();
        newInventory.setSize(inventorySize);

        //Criando itens hardcoded para testar o resto das coisas...
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 5000), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.iron, 5000), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.steel, 5000), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.carbonFiber, 500), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.copper, 5000), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.money, 5000), newInventory);
        newPlayer.setInventory(newInventory);

        newPlayer.setCurrentIslandID(islandService.createIsland());

        for(int i = 0; i < random.nextInt(5 - 3) + 3; i++) {
            newPlayer.setCloseIslandsID(islandService.createIsland());
        }


        return newPlayer;
    }


    public UserEntity craftBoat(BoatEntity desiredBoat, UserEntity player) {

        InventoryEntity playerInventory = player.getInventory();
        InventoryEntity requiredToCraft = desiredBoat.getRequiredToCraft();

        if(inventoryService.isItemsContained(desiredBoat.getRequiredToCraft(), playerInventory)) {
            playerInventory = inventoryService.subtractFromInventory(requiredToCraft, playerInventory);
            player.setInventory(playerInventory);
            BoatDTO boatDTO = conversions.EntityToDTO(desiredBoat);
            player.getBoats().add(boatDTO);
            return player;
        } else {
            return null;
        }

    }



}
