package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.MarketDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.market.MarketEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class DTOEntityConversions {

    public static UserDTO EntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userEntity.getName());
        userDTO.setInventory(userEntity.getInventory());
        for(BoatEntity boat : userEntity.getBoats()) {
            userDTO.setBoats(EntityToDTO(boat));
        }
        userDTO.setCurrentIsland(userEntity.getCurrentIsland());
        userDTO.setCloseIslands(userEntity.getCloseIslands());

        return userDTO;
    }

    public static BoatDTO EntityToDTO(BoatEntity boatEntity) {
        BoatDTO boatDTO = new BoatDTO();

        boatDTO.setCargo(boatEntity.getCargo());
        boatDTO.setId(boatEntity.getId());
        boatDTO.setMaxDistance(boatEntity.getMaxTravelDistance());
        boatDTO.setType(boatEntity.getType());
        boatDTO.setTotalDistanceTravaled(boatEntity.getTotalDistanceTravaled());
        boatDTO.setHealth(boatEntity.getHealth());

        return boatDTO;
    }

    public static MarketDTO EntityToDTO(MarketEntity marketEntity) {
        MarketDTO marketDTO = new MarketDTO();

        marketDTO.setOfferedItem(marketEntity.getOfferedItem());
        marketDTO.setPricePerItem(marketEntity.getPricePerItem());
        marketDTO.setTradeID(marketEntity.getTradeID());

        return marketDTO;
    }


}
