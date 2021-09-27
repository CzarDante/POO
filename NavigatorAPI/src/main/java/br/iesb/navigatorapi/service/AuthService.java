package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.UserEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class AuthService {

    public List<UserEntity> users = new ArrayList<UserEntity>();

    public UserEntity findUserByToken(String token){
        for (UserEntity u : users) {
            if(token.contains(u.getToken())){
                return u;
            }
        }
        return null;
    }

    public String signup(String username) {

        // Nome sem caractere especial

        if (username.trim().equals("")) {
            // Erro de nome vazio
            // return 1;
        }

        if (username.trim().split(" ").length != 1) {
            // Erro de nome com espaço
            // return 2;
        }

        if (username.contains("@")) {
            // Erro de nome com caractere especial
            // return 3;
        }

        UserEntity entity = new UserEntity();
        entity.setName(username);
        String token = UUID.randomUUID().toString();
        entity.setToken(token);
        users.add(entity);
        return entity.getToken();

    }
}
