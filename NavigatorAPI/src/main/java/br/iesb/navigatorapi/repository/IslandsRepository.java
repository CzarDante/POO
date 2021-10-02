package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.island.CarbonFiberIslandEntity;
import br.iesb.navigatorapi.model.island.CopperIslandEntity;
import br.iesb.navigatorapi.model.island.IronIslandEntity;
import br.iesb.navigatorapi.model.island.SteelIslandEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class IslandsRepository {

    private List<CarbonFiberIslandEntity> carbonFiberIslandsInMemory = new ArrayList<>();
    private List<CopperIslandEntity> copperIslandsInMemory = new ArrayList<>();
    private List<IronIslandEntity> ironIslandsInMemory = new ArrayList<>();
    private List<SteelIslandEntity> steelIslandsInMemory = new ArrayList<>();

    public List<CarbonFiberIslandEntity> getCarbonFiberIslandsInMemory() {
        return carbonFiberIslandsInMemory;
    }

    public void setCarbonFiberIslandsInMemory(CarbonFiberIslandEntity carbonFiberIslandsInMemory) {
        this.carbonFiberIslandsInMemory.add(carbonFiberIslandsInMemory);
    }

    public List<CopperIslandEntity> getCopperIslandsInMemory() {
        return copperIslandsInMemory;
    }

    public void setCopperIslandsInMemory(CopperIslandEntity copperIslandsInMemory) {
        this.copperIslandsInMemory.add(copperIslandsInMemory);
    }

    public List<IronIslandEntity> getIronIslandsInMemory() {
        return ironIslandsInMemory;
    }

    public void setIronIslandsInMemory(IronIslandEntity ironIslandsInMemory) {
        this.ironIslandsInMemory.add(ironIslandsInMemory);
    }

    public List<SteelIslandEntity> getSteelIslandsInMemory() {
        return steelIslandsInMemory;
    }

    public void setSteelIslandsInMemory(SteelIslandEntity steelIslandsInMemory) {
        this.steelIslandsInMemory.add(steelIslandsInMemory);
    }
}
