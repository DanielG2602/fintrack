package com.fintrack.controller;

import com.fintrack.entity.User;
import com.fintrack.repository.UserRepository;
import com.fintrack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fintrack.dto.RegisterRequest;
import com.fintrack.dto.LoginRequest;
import com.fintrack.dto.AuthResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }

        User newUser = new User();
        newUser.setNome(request.nome());
        newUser.setEmail(request.email());
        newUser.setSenha(passwordEncoder.encode(request.senha()));
        newUser.setRole(request.role() != null ? request.role() : "ROLE_USER");

        userRepository.save(newUser);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha(), user.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken) {
        if (jwtUtil.validateRefreshToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            String newAccessToken = jwtUtil.generateToken(username);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, refreshToken));
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token inválido ou expirado");
    }
}
