package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.BoatDTO;
import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.service.navigation.NavigationService;
import br.iesb.navigatorapi.service.player.AuthService;
import br.iesb.navigatorapi.service.boat.BoatService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.player.PlayerService;
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
            return ResponseEntity.status(404).body("User does not exists");
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
                return ResponseEntity.status(401).body("This user's gathering is still on cooldown. Try again later.");
        }

        return ResponseEntity.ok().body("Resources gathered successfully!");
    }

    @PostMapping("/navigate")
    public ResponseEntity navigate(@RequestHeader("Token") String token, @RequestParam("boatID") String boatID, @RequestParam("islandID") String islandID) {
        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.notFound().build();
        }

        int errorHandler = NavigationService.navigateToIsland(authToken, boatID, islandID);
        switch (errorHandler){
            case 1:
                return ResponseEntity.status(404).body("Couldn't find any close islands with the informed ID.");
            case 2:
                return ResponseEntity.status(404).body("Couldn't find any boats with the informed ID.");
            case 3:
                return ResponseEntity.status(401).body("Can't use the boat with the informed ID.");
            case 4:
                return ResponseEntity.status(400).body("The distance to the island is greater than the max travel distance of the boat.");
        }

        return ResponseEntity.ok().body("Navigated successfully!");

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
