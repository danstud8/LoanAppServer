package com.demo.loansbackapp.services;


import com.demo.loansbackapp.config.JwtService;
import com.demo.loansbackapp.dto.AuthenticationRequest;
import com.demo.loansbackapp.dto.AuthenticationResponse;
import com.demo.loansbackapp.dto.RegistrationRequest;
import com.demo.loansbackapp.entities.User;
import com.demo.loansbackapp.enums.Role;
import com.demo.loansbackapp.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registerUser(RegistrationRequest request) {
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user, user.getRole());

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse registerAdmin(RegistrationRequest request) {
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user, user.getRole());
        return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow();
        return new AuthenticationResponse(jwtService.generateToken(user, user.getRole()));
    }

}
