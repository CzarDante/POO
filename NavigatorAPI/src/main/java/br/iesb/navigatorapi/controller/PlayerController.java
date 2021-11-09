package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.BoatService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.PlayerService;
import br.iesb.navigatorapi.service.island.IslandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

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
    @Autowired
    private IslandService islandService;

    @PostMapping("/profile")
    public ResponseEntity profile(@RequestHeader("Token") String token) {

        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.status(400).body("User does not exists");
        }
        UserDTO filteredUser = conversions.EntityToDTO(authToken);
        return ResponseEntity.ok().body(filteredUser);
    }

    @PostMapping("/create-boat")
    public ResponseEntity<BoatDTO> createBoat(@RequestHeader("Token") String token, @RequestParam BoatEntity.boatID id) {


        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.notFound().build();
        }

        BoatEntity desiredBoat = boatService.createBoat(id, token);
        if(!playerService.craftBoat(desiredBoat, authToken)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(conversions.EntityToDTO(desiredBoat));
    }

    @PostMapping("/gather-resource")
    public ResponseEntity gatherResource(@RequestHeader("Token") String token, @RequestHeader("Time") Integer time) {

        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null) {
            return ResponseEntity.notFound().build();
        }

        if(!islandService.gatherResource(authToken,time)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body("Resources gathered");
    }



    @DeleteMapping("/delete")
    public ResponseEntity deletePlayer(@RequestParam String id){

        UserEntity authToken = authService.findUserByToken(id);

        if(authToken == null){
            return ResponseEntity.notFound().build();
        }

        authService.deletePlayer(authToken);

        return ResponseEntity.ok().body("User deleted");
    }

    @GetMapping("/list-boats")
    public ResponseEntity listBoats(){

        List<String> items = new ArrayList<>();


        for(BoatEntity.boatID id : BoatEntity.boatID.values()) {
            BoatEntity boat = boatService.createBoat(id, "");
            items.add(boatService.getRequirementToCraft(boat));
        }

        return ResponseEntity.ok().body(items);
    }

}
