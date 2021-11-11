package br.iesb.navigatorapi.service.Market;

import br.iesb.navigatorapi.model.ItemEntity;
import br.iesb.navigatorapi.model.MarketEntity;
import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.service.InventoryService;
import br.iesb.navigatorapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TradingService {

    @Autowired
    private AuthService authService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ItemService itemService;

    public boolean buyItem(int quantity, String tradeID, UserEntity buyer) {

        MarketEntity offer = marketService.findOffer(tradeID);
        if(offer == null) {
            return false;
        }

        UserEntity seller = authService.findUserByToken(offer.getSellerID());
        if(seller == null) {
            return false;
        }

        // Se o usuário tentar comprar mais do que disponível na offer
        if(quantity > offer.getOfferedItem().getQuantity()) {
            quantity = offer.getOfferedItem().getQuantity();
        }

        // Calculando quantidade de itens necessários para comprar
        int requiredToBuy = quantity * offer.getPricePerItem().getQuantity();

        // Vendo se o comprador tem o suficiente para comprar
        ItemEntity buyerItemToPay;
        buyerItemToPay = inventoryService.getItemInInventory(offer.getPricePerItem().getResource(), buyer.getInventory());
        if(buyerItemToPay.getQuantity() < requiredToBuy) {
            return false;
        }

        // Subtraindo item do inventário do comprador
        itemService.subtractQuantity(requiredToBuy, buyerItemToPay);
        if(buyerItemToPay.getQuantity() <= 0) {
            inventoryService.removeItem(buyerItemToPay.getResource(), buyer.getInventory());
        }

        // Subtraindo item da offer
        itemService.subtractQuantity(quantity, offer.getOfferedItem());
        if(offer.getOfferedItem().getQuantity() <= 0) {
            marketService.removeOffer(offer.getTradeID());
        }

        // Adicionando itens ao inventário do vendedor
        ItemEntity payment = itemService.createItem(offer.getPricePerItem().getResource(), requiredToBuy);
        inventoryService.addItemToInventory(payment, seller.getInventory());

        //Adicionando itens ao inventário do comprador
        ItemEntity product = itemService.createItem((offer.getOfferedItem().getResource()), quantity);
        inventoryService.addItemToInventory(product, buyer.getInventory());

        return true;
    }
}
