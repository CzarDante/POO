package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.BoatService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    private AuthService authService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    DTOEntityConversions conversions;
    @Autowired
    private BoatService boatService;

    @PostMapping("/profile")
    public ResponseEntity profile(@RequestHeader("Token") String token) {

        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.status(400).body("User does not exists");
        }

        UserDTO filteredUser = new UserDTO();
        filteredUser = conversions.EntityToDTO(authToken);
        return ResponseEntity.ok().body(filteredUser);
    }

    @PostMapping("/create-boat")
    public ResponseEntity<BoatEntity> createBoat(@RequestHeader("Token") String token, @RequestParam BoatEntity.boatID id) {


        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.notFound().build();
        }

        BoatEntity sloop = new BoatEntity();
        sloop = boatService.createBoat(id, token);


        UserEntity result = new UserEntity();
        result = playerService.createBoat(sloop, authToken);


        if(result == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(sloop);
    }




}
