package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
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

@RestController
public class PlayerController {
    @Autowired
    private AuthService authService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private BoatService boatService;

    @PostMapping("/profile")
    public ResponseEntity profile(@RequestHeader("Token") String token) {

        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.status(400).body("User does not exists");
        }
        UserDTO filteredUser = DTOEntityConversions.EntityToDTO(authToken);
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

        return ResponseEntity.ok().body(DTOEntityConversions.EntityToDTO(desiredBoat));
    }

    @PostMapping("/gather-resource")
    public ResponseEntity gatherResource(@RequestHeader("Token") String token, @RequestHeader("Time") Integer time) {

        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null) {
            return ResponseEntity.notFound().build();
        }

        int errorHandler = IslandService.gatherResource(authToken, time);
        switch(errorHandler) {
            case 1:
                return ResponseEntity.status(400).body("There isn't any resources at the island");
            case 2:
                return ResponseEntity.status(400).body("This user's gathering is still on cooldown. Try again later.");
        }

        return ResponseEntity.ok().body("Resources gathered successfully!");
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
