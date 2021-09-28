package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.model.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    public ItemEntity createItem(ItemEntity.ItemID id, int quantity) {
        ItemEntity newItem = new ItemEntity();
        newItem.setQuantity(quantity);
        newItem.setResource(id);
        return newItem;
    }

    public ItemEntity subtractQuantity(int quantitySubtrahend, ItemEntity itemMain) {
        int result = itemMain.getQuantity() - quantitySubtrahend;
        itemMain.setQuantity(result);
        return itemMain;
    }

    public ItemEntity addQuantity(int quantityAddend, ItemEntity itemMain) {
        int result = itemMain.getQuantity() + quantityAddend;
        itemMain.setQuantity(result);
        return itemMain;
    }


    public boolean isEnoughQuantity(ItemEntity itemToCompare, ItemEntity itemMain) {

        if(itemMain.getQuantity() >= itemToCompare.getQuantity()) {
            return true;
        }
        return false;
    }
}