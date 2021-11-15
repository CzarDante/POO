package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.MarketDTO;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.model.market.MarketEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.service.player.AuthService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.market.MarketService;
import br.iesb.navigatorapi.service.market.TradingService;
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
    @Autowired
    private DTOEntityConversions dtoEntityConversions;
    @Autowired
    private TradingService tradingService;


    @GetMapping("/market/list-all")
    public ResponseEntity listMarketItems(){

        List<MarketEntity> items = this.marketService.getInstance();
        List<MarketDTO> itemsDTO = new ArrayList<MarketDTO>();
        for(MarketEntity itemsAtMarket : items) {
            itemsDTO.add(dtoEntityConversions.EntityToDTO(itemsAtMarket));
        }

        return ResponseEntity.ok().body(itemsDTO);
    }

    @PostMapping ("/market/list-specific")
    public ResponseEntity listSpecificMarketItems(@RequestParam ItemEntity.ItemID resource){
        List<MarketEntity> foundOffers = marketService.findOffers(resource);
        List<MarketDTO> foundOffersDTO = new ArrayList<MarketDTO>();
        for(MarketEntity itemFound : foundOffers) {
            foundOffersDTO.add(dtoEntityConversions.EntityToDTO(itemFound));
        }

        return ResponseEntity.ok().body(foundOffersDTO);
    }

    @PostMapping("/market/sell-item")
    public ResponseEntity addMarketItem(@RequestBody MarketEntity item){

        UserEntity player = this.authService.findUserByToken(item.getSellerID());

        if(player == null){
            return ResponseEntity.status(400).body("User does not exists");
        }

        MarketEntity response = this.marketService.postOffer(item, player);

        if(response == null){
            return ResponseEntity.status(400).body("User does not have enought resouces");
        }

        MarketDTO marketDTO = dtoEntityConversions.EntityToDTO(response);
        return ResponseEntity.ok().body(marketDTO);
    }

    @PostMapping("/market/buy-item")
    public ResponseEntity buyMarketItem(@RequestHeader("Token") String token, @RequestParam int quantity, @RequestParam String tradeID) {
        UserEntity buyer = authService.findUserByToken(token);
        if(buyer == null) {
            return ResponseEntity.notFound().build();
        }

        int errorHandler = tradingService.buyItem(quantity, tradeID, buyer);
        switch(errorHandler) {
            case 1:
                return ResponseEntity.status(400).body("There isn't any offers in the market with the informed tradeID");
            case 2:
                return ResponseEntity.status(400).body("It seems the seller for that trade doesn't exist anymore. The offer has been removed.");
            case 3:
                return ResponseEntity.status(400).body("You don't have enough items to finish the trade.");
        }

        return ResponseEntity.ok().body("Successful trade!");
    }


}
