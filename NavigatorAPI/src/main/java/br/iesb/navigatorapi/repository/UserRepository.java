package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.UserEntity;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Scope("singleton")
public class UserRepository {

    private List<UserEntity> userRepositoryInMemory = new ArrayList<UserEntity>();

    public List<UserEntity> getUserRepositoryInMemory() {
        return userRepositoryInMemory;
    }

    public void setUserRepositoryInMemory(UserEntity userRepositoryInMemory) {
        this.userRepositoryInMemory.add(userRepositoryInMemory);
    }
}
