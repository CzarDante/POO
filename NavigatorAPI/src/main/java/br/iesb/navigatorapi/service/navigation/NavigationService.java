package br.iesb.navigatorapi.service.navigation;

import br.iesb.navigatorapi.model.boat.BoatEntity;
import br.iesb.navigatorapi.model.island.IslandEntity;
import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.service.boat.BoatService;
import br.iesb.navigatorapi.service.island.IslandService;
import br.iesb.navigatorapi.service.player.PlayerService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NavigationService {

    public static int navigateToIsland(UserEntity player, String boatToUseID, String islandID) {

        BoatEntity boat = BoatService.findBoat(player, boatToUseID);
        IslandEntity islandToTravelTo = IslandService.getPlayerIsland(player, islandID);

        int result = canTravel(player, boat, islandID, islandToTravelTo);
        if(result != 0) {
            return result;
        }

        player.setCurrentIsland(islandToTravelTo);
        PlayerService.removePlayerCloseIslands(player);
        PlayerService.createRandomCloseIslands(player);


        return 0;
    }

    private static int canTravel(UserEntity player, BoatEntity boat, String islandID, IslandEntity island) {

        if(!IslandService.isIslandClose(player, islandID)) {
            // Erro: A ilha não está próxima ou não existe
            return 1;
        }

        if(boat == null) {
            // Erro: O barco não existe
            return 2;
        }

        if(!BoatService.isBoatUsable(boat)) {
            // Erro: O barco não é utilizável
            return 3;
        }

        if(island.getDistance() > boat.getMaxTravelDistance()) {
            // Erro: O barco não consegue chegar até a ilha
            return 4;
        }

        return 0;
    }

}
