package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.model.security.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}
