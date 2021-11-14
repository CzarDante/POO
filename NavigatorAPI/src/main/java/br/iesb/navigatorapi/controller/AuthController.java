package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.model.player.UserEntity;
import br.iesb.navigatorapi.service.player.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/create")
    public ResponseEntity<String> signup(@RequestBody UserEntity user) {
        String userToken = service.signup(user.getName());

        if(userToken == null)
            return ResponseEntity.notFound().build();


        return ResponseEntity.ok().body(userToken);
    }

}
