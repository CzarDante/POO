package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Scope("singleton")
public class PlayerService {

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
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 5), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.metal, 10), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.money, 10), newInventory);
        newInventory = inventoryService.addItemToInventory(itemService.createItem(ItemEntity.ItemID.wood, 5), newInventory);
        newPlayer.setInventory(newInventory);

        return newPlayer;
    }

    /*
    public BoatEntity createBoat(UserEntity player){


        if(inventoryService.isItemsContained(player.getInventory(), inventoryBarcoRequiredToCraft)) {
            inventoryPlayer = inventoryService.subtractFromInventory(inventoryBarcoRequiredToCraft, inven)
        }

        if(player.getWood() >= 500 && player.getMetal() >= 500){
            player.setMetal(player.getMetal() - 500);
            player.setWood(player.getWood() - 500);

            BoatEntity boat = new BoatEntity();
            boat.setId(UUID.randomUUID().toString());
            boat.setMaxDistance(10);
            boat.setMaxLoad(5000);
            boat.setSize(5);

            return boat;
        }
        return null;
    }
    */

    public UserEntity createBoat(BoatEntity desiredBoat, UserEntity player) {

        InventoryEntity playerInventory = new InventoryEntity();
        playerInventory = player.getInventory();

        InventoryEntity requiredToCraft = new InventoryEntity();
        requiredToCraft = desiredBoat.getRequiredToCraft();

        if(inventoryService.isItemsContained(desiredBoat.getRequiredToCraft(), playerInventory)) {
            playerInventory = inventoryService.subtractFromInventory(requiredToCraft, playerInventory);
            player.setInventory(playerInventory);
            BoatDTO boatDTO = new BoatDTO();
            boatDTO = conversions.EntityToDTO(desiredBoat);
            player.getBoats().add(boatDTO);
            return player;
        } else {
            return null;
        }

    }



}
