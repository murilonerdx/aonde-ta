package com.murilonerdx.aondeta.resources;

import com.murilonerdx.aondeta.dto.AuthenticationDTO;
import com.murilonerdx.aondeta.dto.JwtTokenDTO;
import com.murilonerdx.aondeta.entities.User;
import com.murilonerdx.aondeta.repositories.UserRepository;
import com.murilonerdx.aondeta.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Api(tags = "Endpoint de autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @Operation(summary = "Autenticar usuario e retornar token")
    @SuppressWarnings("rawtypes")
    @PostMapping()
    public ResponseEntity signin(@RequestBody AuthenticationDTO credentialDTO) {
        try {
            String email = credentialDTO.getEmail();
            String pasword = credentialDTO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pasword));

            User user = repository.findByEmail(email).get();
            JwtTokenDTO jwtToken = new JwtTokenDTO();

            jwtToken.setEmail(user.getEmail());

            if (user != null) {
                jwtToken.setToken(tokenProvider.createToken(email, user.getRoles()));
            } else {
                throw new UsernameNotFoundException("Username " + email + " not found!");
            }
            return ResponseEntity.ok(jwtToken);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @Operation(summary = "Criar usuario")
    @SuppressWarnings("rawtypes")
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AuthenticationDTO credentialDTO) {
        User user = repository.findByEmail(credentialDTO.getEmail()).orElse(null);
        User newUser = new User();

        if(user!=null)
            throw new RuntimeException("E-mail já existe");

        newUser.setId(null);
        newUser.setEmail(credentialDTO.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(credentialDTO.getPassword()));
        repository.save(newUser);

        return ResponseEntity.noContent().build();
    }
}
