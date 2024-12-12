package com.example.demo.service;

import com.example.demo.dto.FavouritesDTO;
import com.example.demo.entity.Favourites;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.FavouritesRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouritesService {

    private final FavouritesRepository favouritesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<FavouritesDTO> getAllFavourites() {
        return favouritesRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public FavouritesDTO getFavouriteById(Long id) {
        Favourites favourite = favouritesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Favourite not found"));
        return mapToDTO(favourite);
    }

    public FavouritesDTO createFavourite(FavouritesDTO favouritesDTO) {
        // Check if the user exists
        User user = userRepository.findById(favouritesDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + favouritesDTO.getUserId() + " not found"));

        // Check if the post exists
        Post post = postRepository.findById(favouritesDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post with ID " + favouritesDTO.getPostId() + " not found"));

        // Create and save favourite
        Favourites favourite = new Favourites();
        favourite.setUserId(user);
        favourite.setPostId(post);
        favourite.setCreatedAt(LocalDateTime.now());

        return mapToDTO(favouritesRepository.save(favourite));
    }

    public void deleteFavourite(Long id) {
        favouritesRepository.deleteById(id);


    }

    private FavouritesDTO mapToDTO(Favourites favourites) {
        FavouritesDTO dto = new FavouritesDTO();
        dto.setId(favourites.getId());
        dto.setUserId(favourites.getUserId().getId());
        dto.setPostId(favourites.getPostId().getId());
        dto.setCreatedAt(favourites.getCreatedAt().toString());
        return dto;
    }
}






