package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.player.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class UserRepository {

    private List<UserEntity> userRepositoryInMemory = new ArrayList<UserEntity>();

    public List<UserEntity> getUserRepositoryInMemory() {
        return userRepositoryInMemory;
    }

    public void setUserRepositoryInMemory(UserEntity userRepositoryInMemory) {
        this.userRepositoryInMemory.add(userRepositoryInMemory);
    }

    public void removeUserRepositoryInMemory(UserEntity userRepositoryInMemory) {
        this.userRepositoryInMemory.remove(userRepositoryInMemory);
    }
}
