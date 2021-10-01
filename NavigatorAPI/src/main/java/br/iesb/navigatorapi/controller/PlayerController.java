package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.BoatService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.PlayerService;
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
    DTOEntityConversions conversions;
    @Autowired
    private BoatService boatService;

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
    public ResponseEntity<BoatEntity> createBoat(@RequestHeader("Token") String token, @RequestParam BoatEntity.boatID id) {


        UserEntity authToken = authService.findUserByToken(token);

        if(authToken == null){
            return ResponseEntity.notFound().build();
        }


        BoatEntity desiredBoat = boatService.createBoat(id, token);

        UserEntity result = playerService.craftBoat(desiredBoat, authToken);


        if(result == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(desiredBoat);
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

        String sloop = "Type: sloopMax Distance:100 Wood: 500";
        String sailboat = "Type: sailboat  Max Distance:200 Wood: 1000 Iron: 200";
        String brigantine = "Type: brigantine Max Distance:300 Wood: 1500 Copper: 200";
        String galleon = "Type: brigantine Max Distance:400 Wood: 2000 Copper: 200 Iron: 200 Steel: 200 Carbon Fiber: 200";

        items.add(sloop);
        items.add(sailboat);
        items.add(brigantine);
        items.add(galleon);

        return ResponseEntity.ok().body(items);
    }

}
