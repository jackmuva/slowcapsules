package com.jackmu.slowcapsules.service.security;

import com.jackmu.slowcapsules.exception.SlowCapsuleAPIException;
import com.jackmu.slowcapsules.jwt.JwtTokenProvider;
import com.jackmu.slowcapsules.model.security.LoginDTO;
import com.jackmu.slowcapsules.model.security.RegisterDTO;
import com.jackmu.slowcapsules.model.security.Role;
import com.jackmu.slowcapsules.model.security.User;
import com.jackmu.slowcapsules.repository.security.RoleRepository;
import com.jackmu.slowcapsules.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) throws Exception{
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new SlowCapsuleAPIException(HttpStatus.BAD_REQUEST, "email already exists");
        }

        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new SlowCapsuleAPIException(HttpStatus.BAD_REQUEST, "username already exists");
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registerd successfully";

    }


}
