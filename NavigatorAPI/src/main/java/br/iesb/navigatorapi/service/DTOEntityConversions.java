package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class DTOEntityConversions {

    public UserDTO EntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(userEntity.getName());
        userDTO.setWood(userEntity.getWood());
        userDTO.setBoats(userEntity.getBoats());
        userDTO.setMetal(userEntity.getMetal());
        userDTO.setMoney(userEntity.getMoney());

        return userDTO;
    }
}
