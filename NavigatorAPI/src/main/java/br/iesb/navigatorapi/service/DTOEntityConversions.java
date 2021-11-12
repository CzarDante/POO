package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.MarketDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.MarketEntity;
import br.iesb.navigatorapi.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class DTOEntityConversions {

    public static UserDTO EntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userEntity.getName());
        userDTO.setInventory(userEntity.getInventory());
        userDTO.setBoats(userEntity.getBoats());
        userDTO.setCurrentIsland(userEntity.getCurrentIsland());
        userDTO.setCloseIslands(userEntity.getCloseIslands());

        return userDTO;
    }

    public static BoatDTO EntityToDTO(BoatEntity boatEntity) {
        BoatDTO boatDTO = new BoatDTO();

        boatDTO.setCargo(boatEntity.getCargo());
        boatDTO.setId(boatEntity.getId());
        boatDTO.setMaxDistance(boatEntity.getMaxDistance());
        boatDTO.setType(boatEntity.getType());
        boatDTO.setTotalDistanceTravaled(boatEntity.getTotalDistanceTravaled());

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
