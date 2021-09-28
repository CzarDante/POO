package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class DTOEntityConversions {

    public UserDTO EntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userEntity.getName());
        userDTO.setInventory(userEntity.getInventory());
        //userDTO.setWood(userEntity.getWood());
        userDTO.setBoats(userEntity.getBoats());
        //userDTO.setMetal(userEntity.getMetal());
        //userDTO.setMoney(userEntity.getMoney());

        return userDTO;
    }

    public BoatDTO EntityToDTO(BoatEntity boatEntity) {
        BoatDTO boatDTO = new BoatDTO();

        boatDTO.setCargo(boatEntity.getCargo());
        boatDTO.setId(boatEntity.getId());
        boatDTO.setMaxDistance(boatEntity.getMaxDistance());
        boatDTO.setType(boatEntity.getType());
        boatDTO.setTotalDistanceTravaled(boatEntity.getTotalDistanceTravaled());

        return boatDTO;
    }


}
