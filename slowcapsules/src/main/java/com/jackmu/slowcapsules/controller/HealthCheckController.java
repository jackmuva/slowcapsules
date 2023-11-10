package com.jackmu.slowcapsules.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HealthCheckController {
    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> getHealthcheckMessage(){
        return new ResponseEntity<>("Welcome to Trtlmail", HttpStatus.OK);
    }
}
