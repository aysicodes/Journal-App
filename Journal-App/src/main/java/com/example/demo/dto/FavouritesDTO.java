package com.example.demo.dto;

import lombok.Data;


@Data
public class FavouritesDTO {
    private Long id;

    private Long userId;

    private Long postId;

    private String createdAt;
}

