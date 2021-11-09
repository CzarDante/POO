package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.model.BoatEntity;
import br.iesb.navigatorapi.model.MerchandiseEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.repository.MarketRepository;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MarketController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private AuthService authService;

    @GetMapping("/market")
    public ResponseEntity listMarketItems(){

        List<MerchandiseEntity> items = this.marketService.getInstance();

        return ResponseEntity.ok().body(items);
    }

    @PostMapping("/market")
    public ResponseEntity addMarketItem(@RequestBody MerchandiseEntity item){

        UserEntity player = this.authService.findUserByToken(item.getPlayerOffer());

        if(player == null){
            return ResponseEntity.status(400).body("User does not exists");
        }

        MerchandiseEntity response = this.marketService.postOffer(item, player);

        if(response == null){
            return ResponseEntity.status(400).body("User does not have enought resouces");
        }

        return ResponseEntity.ok().body(response);
    }
}
