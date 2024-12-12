package com.example.demo.controller;

import com.example.demo.dto.FavouritesDTO;
import com.example.demo.service.FavouritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouritesController {

    private final FavouritesService favouritesService;

    @GetMapping
    public ResponseEntity<List<FavouritesDTO>> getAllFavourites() {
        List<FavouritesDTO> favourites = favouritesService.getAllFavourites();
        return ResponseEntity.ok(favourites);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavouritesDTO> getFavouriteById(@PathVariable Long id) {
        FavouritesDTO favourite = favouritesService.getFavouriteById(id);
        return ResponseEntity.ok(favourite);
    }

    @PostMapping
    public ResponseEntity<FavouritesDTO> createFavourite(@RequestBody FavouritesDTO favouritesDTO) {
        FavouritesDTO createdFavourite = favouritesService.createFavourite(favouritesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFavourite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable Long id) {
        favouritesService.deleteFavourite(id);
        return ResponseEntity.noContent().build();
    }
}