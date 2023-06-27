package com.jackmu.slowcapsules.controller;

import com.jackmu.slowcapsules.model.UserDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

//    @PostMapping("/user/registration")
//    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
//        LOGGER.debug("Registering user account with information: {}", accountDto);
//
//        final User registered = userService.registerNewUserAccount(accountDto);
//        userService.addUserLocation(registered, getClientIP(request));
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
//        return new GenericResponse("success");
//    }
}
