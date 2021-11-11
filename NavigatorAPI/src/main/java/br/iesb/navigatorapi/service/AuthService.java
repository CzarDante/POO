package br.iesb.navigatorapi.service;

import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Scope("singleton")
public class AuthService {

    public UserRepository userRepository;

    @Autowired
    public PlayerService playerService;

    public AuthService(){
        this.userRepository = new UserRepository();
    }


    public UserEntity findUserByToken(String token){
        for (UserEntity u : userRepository.getUserRepositoryInMemory()) {
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
            return null;
        }

        if (username.trim().split(" ").length != 1) {
            // Erro de nome com espaço
            return null;
        }

        Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(username);
        boolean usernameHasSpecialCharacters = m.find();

        if(usernameHasSpecialCharacters) {
            // Erro de nome com caractere especial
            return null;
        }

        String token = UUID.randomUUID().toString();
        UserEntity newPlayer = playerService.createPlayer(token, username, 10);
        userRepository.setUserRepositoryInMemory(newPlayer);
        return newPlayer.getToken();

    }

    public void deletePlayer(UserEntity u){
        this.userRepository.removeUserRepositoryInMemory(u);
    }
}
