package br.iesb.navigatorapi.service.inventory;

import br.iesb.navigatorapi.model.inventory.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    public static ItemEntity createItem(ItemEntity.ItemID id, int quantity) {
        ItemEntity newItem = new ItemEntity();
        newItem.setQuantity(quantity);
        newItem.setResource(id);
        return newItem;
    }

    public static void subtractQuantity(int quantitySubtrahend, ItemEntity itemMain) {
        int result = itemMain.getQuantity() - quantitySubtrahend;
        itemMain.setQuantity(result);
    }

    public static void addQuantity(int quantityAddend, ItemEntity itemMain) {
        int result = itemMain.getQuantity() + quantityAddend;
        itemMain.setQuantity(result);
    }

    public static boolean isEnoughQuantity(ItemEntity itemToCompare, ItemEntity itemMain) {

        if(itemMain.getQuantity() >= itemToCompare.getQuantity()) {
            return true;
        }
        return false;
    }

    public static boolean isEnoughQuantity(int quantity, ItemEntity itemMain) {

        if(itemMain.getQuantity() >= quantity) {
            return true;
        }
        return false;
    }
}
