package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String profileImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
