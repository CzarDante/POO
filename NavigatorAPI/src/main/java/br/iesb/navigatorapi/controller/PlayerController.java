package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private AuthService service;
    @Autowired
    private PlayerService playerService;
    @Autowired
    DTOEntityConversions conversions;

    @PostMapping("/profile")
    public ResponseEntity profile(@RequestHeader("Token") String token) {

        UserEntity authToken = service.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.status(400).body("User does not exists");
        }

        UserDTO filteredUser = new UserDTO();
        filteredUser = conversions.EntityToDTO(authToken);
        return ResponseEntity.ok().body(filteredUser);
    }

    @PostMapping("/create-boat")
    public ResponseEntity createBoat(@RequestHeader("Token") String token) {

        UserEntity authToken = service.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.notFound().build();
        }


        BoatEntity boat = playerService.createBoat(authToken);

        if(boat == null){
            return ResponseEntity.status(400).body("Insufficient resources");
        }

        authToken.setBoats(boat);

        return ResponseEntity.ok().body(boat);
    }
}
