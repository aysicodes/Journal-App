package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Long id;

    private String title;

    private String content;

    private String status;

    private String image;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
