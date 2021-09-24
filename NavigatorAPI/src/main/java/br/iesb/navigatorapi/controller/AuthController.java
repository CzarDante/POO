package br.iesb.navigatorapi.controller;

import br.iesb.navigatorapi.model.UserEntity;
import br.iesb.navigatorapi.service.AuthService;
import br.iesb.navigatorapi.dto.UserDTO;
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
    public void signup(@RequestBody UserEntity user) {

        service.signup(user.getName());

    }

    @PostMapping("/login")
    public void login(@RequestBody String token) {
        service.login(token);
    }

}
