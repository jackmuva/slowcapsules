package com.jackmu.slowcapsules.controller.security;

import com.jackmu.slowcapsules.model.security.LoginDTO;
import com.jackmu.slowcapsules.model.security.RegisterDTO;
import com.jackmu.slowcapsules.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        String response = authService.login(loginDTO);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) throws Exception{
        String response = authService.register(registerDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
