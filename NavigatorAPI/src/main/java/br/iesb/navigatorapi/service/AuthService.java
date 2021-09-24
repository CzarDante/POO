package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.dto.UserDTO;
import br.iesb.navigatorapi.model.NavigatorEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope("singleton")
public class AuthService {

    public List<NavigatorEntity> user = new ArrayList<>();

    public String login(UserDTO info){
        
        String name = info.getName();
        String token = info.getToken();

        for(NavigatorEntity u : user){
            if(u.getName()){
                return u.getToken();
            }
        }

    }

    public int signup(UserDTO user) {

        // Nome sem caractere especial

        //nome vazio
        if(user.getName().trim().equals("")) {
            //Erro de nome vazio
            return 1;
        }

        //Nome sem espaço
        if(user.getName().trim().split(" ").length != 1) {
            //Erro de nome com espaço
            return 2;
        }

        //Checar se nome de usuário já existe
        // Receber todos os usuários
        // For each
        // Return 3

            

        NavigatorEntity entity = new NavigatorEntity();
        entity.setName(user.getName());
        String token = UUID.randomUUID().toString();
        entity.setToken(token);

        return 0;
    }
}

