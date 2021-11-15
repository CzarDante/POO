package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.island.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class IslandsRepository {

    private List<IslandEntity> islandsInMemory = new ArrayList<>();

    public List<IslandEntity> getIslandsInMemory() {
        return islandsInMemory;
    }

    public void setIslandsInMemory(IslandEntity islandsInMemory) {
        this.islandsInMemory.add(islandsInMemory);
    }
}
