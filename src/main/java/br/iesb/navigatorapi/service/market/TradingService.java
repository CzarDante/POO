package br.iesb.navigatorapi.service.market;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import br.iesb.navigatorapi.model.market.MarketEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.service.player.AuthService;
import br.iesb.navigatorapi.service.inventory.InventoryService;
import br.iesb.navigatorapi.service.inventory.ItemService;
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

    public int buyItem(int quantity, String tradeID, UserEntity buyer) {

        MarketEntity offer = marketService.findOffer(tradeID);
        if(offer == null) {
            // Erro: Não encontrou a oferta
            return 1;
        }

        UserEntity seller = authService.findUserByToken(offer.getSellerID());
        if(seller == null) {
            // Erro: Não encontrou o vendedor
            marketService.removeOffer(offer.getTradeID());
            return 2;
        }

        // Se o usuário tentar comprar mais do que disponível na offer
        if(quantity > offer.getOfferedItem().getQuantity()) {
            quantity = offer.getOfferedItem().getQuantity();
        }

        // Calculando quantidade de itens necessários para comprar
        int requiredToBuy = quantity * offer.getPricePerItem().getQuantity();

        // Vendo se o comprador tem o suficiente para comprar
        ItemEntity buyerItemToPay;
        buyerItemToPay = InventoryService.getItemInInventory(offer.getPricePerItem().getResource(), buyer.getInventory());
        if(buyerItemToPay.getQuantity() < requiredToBuy) {
            // Erro: O comprador não tem itens o suficiente para comprar
            return 3;
        }

        // Subtraindo item do inventário do comprador
        ItemService.subtractQuantity(requiredToBuy, buyerItemToPay);
        if(buyerItemToPay.getQuantity() <= 0) {
            InventoryService.removeItem(buyerItemToPay.getResource(), buyer.getInventory());
        }

        // Adicionando itens ao inventário do vendedor
        ItemEntity payment = ItemService.createItem(offer.getPricePerItem().getResource(), requiredToBuy);
        InventoryService.addItemToInventory(payment, seller.getInventory());

        //Adicionando itens ao inventário do comprador
        ItemEntity product = ItemService.createItem((offer.getOfferedItem().getResource()), quantity);
        InventoryService.addItemToInventory(product, buyer.getInventory());

        // Subtraindo item da offer
        ItemService.subtractQuantity(quantity, offer.getOfferedItem());
        if(offer.getOfferedItem().getQuantity() <= 0) {
            marketService.removeOffer(offer.getTradeID());
        }

        return 0;
    }

    // Este método apenas transfere o item quando há a quantidade suficiente para ser transferido
    public static boolean transferItem(InventoryEntity inventoryToAdd, InventoryEntity inventoryToRemove, ItemEntity.ItemID itemToExchange, int quantity) {

        if(InventoryService.isItemContained(itemToExchange, inventoryToRemove)) {
            ItemEntity itemToBeRemoved = InventoryService.getItemInInventory(itemToExchange, inventoryToRemove);
            if(ItemService.isEnoughQuantity(quantity, itemToBeRemoved)) {
                ItemEntity transferedItem = ItemService.createItem(itemToExchange, quantity);
                
                InventoryService.subtractFromInventory(transferedItem, inventoryToRemove);
                InventoryService.addItemToInventory(transferedItem, inventoryToAdd);

                return true;
            }
        }
        return false;
    }

    // Este método transfere o item mesmo se não houver a quantidade suficiente
    // Caso não haja a quantidade suficiente, será transferido apenas a quantidade existente no inventário.
    // [Se o item sequer existir no inventário, retornará falso]
    public static boolean forceTransferItem(InventoryEntity inventoryToAdd, InventoryEntity inventoryToRemove, ItemEntity.ItemID itemToExchange, int quantity) {

        if(InventoryService.isItemContained(itemToExchange, inventoryToRemove)) {

            ItemEntity itemToBeRemoved = InventoryService.getItemInInventory(itemToExchange, inventoryToRemove);

            if(!ItemService.isEnoughQuantity(quantity, itemToBeRemoved)) {
                quantity = itemToBeRemoved.getQuantity();
            }

            ItemEntity transferedItem = ItemService.createItem(itemToExchange, quantity);

            InventoryService.subtractFromInventory(transferedItem, inventoryToRemove);
            InventoryService.addItemToInventory(transferedItem, inventoryToAdd);

            return true;

        }
        return false;
    }

}
