package com.jackmu.slowcapsules.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

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
