package br.iesb.navigatorapi.service.boat;

import br.iesb.navigatorapi.model.boat.*;
import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;
import org.springframework.stereotype.Service;

@Service
public class BoatService {

    public BoatEntity createBoat(BoatEntity.boatID type, String id) {
        BoatEntity newBoat;

        switch(type) {
            case sloop:
                newBoat = new SloopBoatEntity();
                return newBoat;
            case sailboat:
                newBoat = new SailboatBoatEntity();
                return newBoat;
            case brigantine:
                newBoat = new BrigantineBoatEntity();
                return newBoat;
            case galleon:
                newBoat = new GalleonBoatEntity();
                return newBoat;
            default:
        }

        return null;
    }

    public String getRequirementToCraft(BoatEntity boat) {

        String formattedString = boat.getType().toString();

        for(ItemEntity item : boat.getRequiredToCraft().getItems()) {
            formattedString += " | ";
            formattedString += item.getResource();
            formattedString += " x";
            formattedString += item.getQuantity();
        }

        return formattedString;
    }

    public static boolean isBoatUsable(BoatEntity boat) {

        if(boat.getHealth() <= 0) {
            return false;
        }

        return true;
    }

    public static BoatEntity findBoat(UserEntity player, String boatID) {
        for(BoatEntity boat : player.getBoats()) {
            if(boat.getId().equals(boatID)) {
                return boat;
            }
        }

        return null;
    }

}
