package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Scope("singleton")
public class PlayerService {

    public BoatEntity createBoat(UserEntity player){

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

}
