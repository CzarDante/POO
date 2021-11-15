package br.iesb.navigatorapi.service.inventory;

import br.iesb.navigatorapi.model.inventory.InventoryEntity;
import br.iesb.navigatorapi.model.inventory.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public static InventoryEntity createInventory(int size) {

        InventoryEntity newInventory = new InventoryEntity();
        newInventory.setSize(size);
        return newInventory;

    }

    public static boolean addItemToInventory(ItemEntity itemToAdd, InventoryEntity inventoryMain) {

        // Procurando o item
        for(ItemEntity itemMain : inventoryMain.getItems()) {

            if(itemMain.getResource() == itemToAdd.getResource()) {
                ItemService.addQuantity(itemToAdd.getQuantity(), itemMain);
                return true;
            }

        }

        //Se o item não existe, verificar se a gente pode colocar ele sem superar o tamanho do inventário
        if(inventoryMain.getItems().size() + 1 > inventoryMain.getSize()) {
            //TODO: Não foi possível adicionar o item pois supera o tamanho do inventário
            return false;
        }

        inventoryMain.getItems().add(itemToAdd);
        return true;
    }

    public static boolean addItemToInventory(InventoryEntity inventoryToAdd, InventoryEntity inventoryMain) {

        for(ItemEntity itemToAdd : inventoryToAdd.getItems()) {
            if(!addItemToInventory(itemToAdd, inventoryMain)) {
                return false;
            }
        }

        return true;
    }

    public static boolean subtractFromInventory(ItemEntity itemSubtrahend, InventoryEntity inventoryMain) {

        //Vendo se o item está contido no inventário
        if(isItemContained(itemSubtrahend.getResource(), inventoryMain)) {

            // Procurando o item
            for(ItemEntity itemMain : inventoryMain.getItems()) {
                if (itemMain.getResource() == itemSubtrahend.getResource()) {

                    //Garantir que dá para subtrair
                    if (ItemService.isEnoughQuantity(itemSubtrahend, itemMain)) {
                        ItemService.subtractQuantity(itemSubtrahend.getQuantity(), itemMain);
                        return true;

                    // Se o player não tem a quantidade suficiente de itens para poder subtrair
                    } else {

                        return false;

                    }

                }
            }

        }

        // Se chegou aqui o item não está contido no inventário.
        return false;

    }

    public static boolean subtractFromInventory(InventoryEntity inventorySubtrahend, InventoryEntity inventoryMain) {

        //Só entra na subtração se o player contem todos os itens necessários
        if(!isItemsContained(inventorySubtrahend, inventoryMain)) {
            return false;
        } else {

            for(ItemEntity itemSubtrahend : inventorySubtrahend.getItems()) {
                subtractFromInventory(itemSubtrahend, inventoryMain);
            }
        }

        return true;
    }

    public static boolean removeItem(ItemEntity.ItemID id, InventoryEntity inventoryMain) {

        //Só entra na remoção se o item está contido no inventário
        if(isItemContained(id, inventoryMain)) {

            for(ItemEntity itemMain : inventoryMain.getItems()) {

                if(itemMain.getResource() == id) {
                    inventoryMain.getItems().remove(itemMain);
                    return true;
                }

            }

        }

        return false;
    }

    public static InventoryEntity copyInventory(InventoryEntity inventoryToCopy) {
        InventoryEntity newInventory = createInventory(inventoryToCopy.getSize());

        ItemEntity itemCopy;
        for(ItemEntity item : inventoryToCopy.getItems()) {
            itemCopy = ItemService.createItem(item.getResource(), item.getQuantity());
            addItemToInventory(itemCopy, newInventory);
        }

        return newInventory;
    }

    public static ItemEntity getItemInInventory(ItemEntity.ItemID id, InventoryEntity inventoryMain) {

        for(ItemEntity item : inventoryMain.getItems()) {
            if(item.getResource() == id) {
                return item;
            }
        }

        return null;
    }

    public static boolean isItemsContained(InventoryEntity inventoryToCompare, InventoryEntity inventoryMain) {

        for(ItemEntity itemToCompare : inventoryToCompare.getItems()) {

            boolean validated = false;
            for(ItemEntity itemMain : inventoryMain.getItems()) {

                if(itemMain.getResource() == itemToCompare.getResource()) {
                    if(ItemService.isEnoughQuantity(itemToCompare,itemMain)) {
                        validated=true;
                        break;
                    }
                }

            }
            if(!validated)
                return false;
        }

        return true;

    }

    public static boolean isItemContained(ItemEntity.ItemID id, InventoryEntity inventoryMain) {

        for(ItemEntity item : inventoryMain.getItems()) {
            if(item.getResource() == id)
                return true;
        }
        return false;
    }

    public static boolean isInventoriesEqual(InventoryEntity inventoryA, InventoryEntity inventoryB) {
        boolean validated = false;
        for(ItemEntity itemA : inventoryA.getItems()) {

            for(ItemEntity itemB : inventoryB.getItems()) {
                //Ver se o recurso existe nos dois inventários
                if(itemA.getResource() == itemB.getResource()) {
                    //Ver se os dois itens estão na mesma quantidade
                    if(itemA.getQuantity() == itemB.getQuantity()) {
                        validated = true;
                    }
                    break;
                }
            }
            if(!validated)
                return false;

        }

        return true;
    }

}
