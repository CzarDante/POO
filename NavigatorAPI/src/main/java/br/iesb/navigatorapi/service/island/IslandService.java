package br.iesb.navigatorapi.service.island;

import br.iesb.navigatorapi.model.island.CarbonFiberIslandEntity;
import br.iesb.navigatorapi.model.island.CopperIslandEntity;
import br.iesb.navigatorapi.model.island.IronIslandEntity;
import br.iesb.navigatorapi.model.island.SteelIslandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class IslandService {

    @Autowired
    CarbonFiberIslandService carbonFiberIslandService;
    @Autowired
    CopperIslandService copperIslandService;
    @Autowired
    SteelIslandService steelIslandService;
    @Autowired
    IronIslandService ironIslandService;


    Random random = new Random();

    private String[] islandTypes = {"Carbon", "Copper", "Iron", "Steel"};

    public String createIsland() {
        switch (islandTypes[random.nextInt(4)]) {
            case "Carbon":
                CarbonFiberIslandEntity carbonFiberIslandEntity = new CarbonFiberIslandEntity();
                carbonFiberIslandEntity = carbonFiberIslandService.createCarbonFiberIslandService();
                return carbonFiberIslandEntity.getId();
            case "Copper":
                CopperIslandEntity copperIslandEntity = new CopperIslandEntity();
                copperIslandEntity = copperIslandService.createCopperIslandService();
                return copperIslandEntity.getId();
            case "Iron":
                IronIslandEntity ironIslandEntity = new IronIslandEntity();
                ironIslandEntity = ironIslandService.createIronIslandService();
                return ironIslandEntity.getId();
            case "Steel":
                SteelIslandEntity steelIslandEntity = new SteelIslandEntity();
                steelIslandEntity = steelIslandService.createSteelIslandService();
                return steelIslandEntity.getId();
        }

        return null;
    }


}
