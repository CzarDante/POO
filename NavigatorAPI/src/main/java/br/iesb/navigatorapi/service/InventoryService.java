package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.model.InventoryEntity;
import br.iesb.navigatorapi.model.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    ItemService itemService = new ItemService();

    public InventoryEntity createInventory(int size) {

        InventoryEntity newInventory = new InventoryEntity();
        newInventory.setSize(size);
        return newInventory;

    }

    public InventoryEntity addItemToInventory(ItemEntity itemToAdd, InventoryEntity inventoryMain) {

        // Procurando o item
        for(ItemEntity itemMain : inventoryMain.getItems()) {

            if(itemMain.getResource() == itemToAdd.getResource()) {
                itemMain = itemService.addQuantity(itemToAdd.getQuantity(), itemMain);
                return inventoryMain;
            }

        }

        //Se o item não existe, verificar se a gente pode colocar ele sem superar o tamanho do inventário
        if(inventoryMain.getItems().size() + 1 > inventoryMain.getSize()) {
            //TODO: Não foi possível adicionar o item pois supera o tamanho do inventário
            return inventoryMain;
        }

        inventoryMain.getItems().add(itemToAdd);
        return inventoryMain;
    }

    public InventoryEntity subtractFromInventory(ItemEntity itemSubtrahend, InventoryEntity inventoryMain) {

        //Vendo se o item está contido no inventário
        if(isItemContained(itemSubtrahend.getResource(), inventoryMain)) {

            // Procurando o item
            for(ItemEntity itemMain : inventoryMain.getItems()) {
                if (itemMain.getResource() == itemSubtrahend.getResource()) {

                    //Garantir que dá para subtrair
                    if (itemService.isEnoughQuantity(itemSubtrahend, itemMain)) {
                        itemMain = itemService.subtractQuantity(itemSubtrahend.getQuantity(), itemMain);

                        //Se a subtração deixar o player com 0 qtd de itens, remover o item do inventário
                        if (itemMain.getQuantity() == 0)
                            inventoryMain = removeItem(itemMain.getResource(), inventoryMain);

                        return inventoryMain;

                    // Se o player não tem a quantidade suficiente de itens para poder subtrair
                    } else {

                        return inventoryMain;

                    }

                }
            }

        }

        // Se chegou aqui o item não está contido no inventário.
        return inventoryMain;

    }

    public InventoryEntity subtractFromInventory(InventoryEntity inventorySubtrahend, InventoryEntity inventoryMain) {

        //Só entra na subtração se o player contem todos os itens necessários
        if(isItemsContained(inventorySubtrahend, inventoryMain)) {

            for(ItemEntity itemSubtrahend : inventorySubtrahend.getItems()) {

                //Procurando o item
                for(ItemEntity itemMain : inventoryMain.getItems()) {
                    if(itemMain.getResource() == itemSubtrahend.getResource()) {

                        //Garantir que dá para subtrair
                        if(itemService.isEnoughQuantity(itemSubtrahend, itemMain)) {

                            itemMain = itemService.subtractQuantity(itemSubtrahend.getQuantity(), itemMain);
                            if(itemMain.getQuantity() == 0)
                                inventoryMain = removeItem(itemMain.getResource(), inventoryMain);

                            break;
                        }
                    }

                }
            }
        }


        return inventoryMain;
    }

    public InventoryEntity removeItem(ItemEntity.ItemID id, InventoryEntity inventoryMain) {

        //Só entra na remoção se o item está contido no inventário
        if(isItemContained(id, inventoryMain)) {

            for(ItemEntity itemMain : inventoryMain.getItems()) {

                if(itemMain.getResource() == id) {
                    inventoryMain.getItems().remove(itemMain);
                    return inventoryMain;
                }

            }

        }

        return inventoryMain;
    }

    public InventoryEntity removeEmptyItems(InventoryEntity inventoryMain) {

        for(ItemEntity itemMain : inventoryMain.getItems()) {

            if(itemMain.getQuantity() == 0) {
                inventoryMain.getItems().remove(itemMain);
            }
        }

        return inventoryMain;
    }

    public ItemEntity findItem (ItemEntity.ItemID id, InventoryEntity inventoryMain) {

        for(ItemEntity item : inventoryMain.getItems()) {
            if(item.getResource() == id) {
                return item;
            }
        }

        return null;
    }

    public boolean isItemsContained(InventoryEntity inventoryToCompare, InventoryEntity inventoryMain) {

        for(ItemEntity itemToCompare : inventoryToCompare.getItems()) {

            boolean validated = false;
            for(ItemEntity itemMain : inventoryMain.getItems()) {

                if(itemMain.getResource() == itemToCompare.getResource()) {
                    if(itemService.isEnoughQuantity(itemToCompare,itemMain)) {
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

    public boolean isItemContained(ItemEntity.ItemID id, InventoryEntity inventoryMain) {

        for(ItemEntity item : inventoryMain.getItems()) {
            if(item.getResource() == id)
                return true;
        }
        return false;
    }

}
