package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

private final UserService userService;


    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


//    private final String UPLOAD_DIR = "uploaded_images/";
//
//    @PostMapping("/{id}/uploadImage")
//    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
//        try {
//            String fileName = file.getOriginalFilename();
//            Path path = Paths.get(UPLOAD_DIR + fileName);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//            String imageUrl = "http://localhost:8080/uploaded_files/" + fileName;
//            return ResponseEntity.ok("Image uploaded successfully: " + imageUrl);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
//        }
//    }
//
//
//    @PutMapping("/update-image/{userId}")
//    public ResponseEntity<Void> updateUserImage(@PathVariable Long userId, @RequestParam String imageUrl) {
//        userService.updateUserImage(userId, imageUrl); // Вызываем метод из UserService
//        return ResponseEntity.ok().build(); // Возвращаем HTTP 200 OK
//    }

//
//    // Регистрация пользователя
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody SignUpDTO signUpDto) {
//        userService.saveUser(signUpDto);
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//    // Логин пользователя
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto) {
//        String token = userService.loginUser(loginDto.getEmail(), loginDto.getPassword());
//        return ResponseEntity.ok(token);
//    }

}
