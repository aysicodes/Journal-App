package com.example.demo.controller;


import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request)); // Роль больше не используется
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request)); // Роль больше не используется
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return authService.refreshToken(request, response); // Роль больше не используется
    }
}







//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;

//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Регистрация пользователя
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody User user) {
//        // Проверка на существующего пользователя
//        if (userRepository.existsByEmail(user.getEmail())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already taken");
//        }
//        userRepository.save(user);  // сохраняет пользователя в базу
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//
//    // Вход пользователя
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        User existingUser = userRepository.findByEmail(user.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!existingUser.getPassword().equals(user.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//        // Генерация токена, если нужно (например, с использованием JWT)
//        String token = generateToken(existingUser);
//
//        return ResponseEntity.ok("Login successful. Token: " + token);
//    }
//
//    private String generateToken(User user) {
//        // Реализация генерации токена (например, JWT)
//        return Jwts.builder()
//                .setSubject(user.getEmail())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 день
//                .signWith(SignatureAlgorithm.HS256, "secret-key")
//                .compact();
//    }
//
//
//
//}
