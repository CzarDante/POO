package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.dto.MarketDTO;
import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.MarketEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.DTOEntityConversions;
import br.iesb.navigatorapi.service.Market.MarketService;
import br.iesb.navigatorapi.service.Market.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.error.Mark;

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

        boolean isValidTrade = tradingService.buyItem(quantity, tradeID, buyer);
        if(!isValidTrade) {
            return ResponseEntity.status(400).body("User can't buy the item.");
        }

        return ResponseEntity.ok().body("Successful trade!");
    }


}
