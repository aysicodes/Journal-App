package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.ImageData;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.StorageRepository;  // Use StorageRepository for image storage
import com.example.demo.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class PostService {
//
//    private final PostRepository postRepository;
//    private final StorageRepository storageRepository;  // Inject StorageRepository to save images
//    private final String IMAGE_STORAGE_PATH = "/images/";  // Specify your storage path if storing images on disk
//
//    // Fetch all posts and map to DTOs
//    public List<PostDTO> getAllPosts() {
//        return postRepository.findAll().stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // Fetch a specific post by ID and return a DTO
//    public PostDTO getPostById(Long id) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//        return mapToDTO(post);
//    }
//
//    // Create a new post with an optional image
//    public PostDTO createPost(PostDTO postDTO, MultipartFile imageFile) throws IOException {
//        Post post = new Post();
//        post.setTitle(postDTO.getTitle());
//        post.setContent(postDTO.getContent());
//        post.setStatus(postDTO.getStatus());
//
//        // Handle image upload if present
//        if (imageFile != null && !imageFile.isEmpty()) {
//            ImageData imageData = uploadImage(imageFile);
//            post.setImage(imageData.getName());  // Save the image name or URL in the post
//        }
//
//        // Save the post
//        Post savedPost = postRepository.save(post);
//        return mapToDTO(savedPost);
//    }
//
//    // Update an existing post with an optional new image
//    public PostDTO updatePost(Long id, PostDTO postDTO, MultipartFile imageFile) throws IOException {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Post not found"));
//
//        post.setTitle(postDTO.getTitle());
//        post.setContent(postDTO.getContent());
//        post.setStatus(postDTO.getStatus());
//
//        // Handle image update if a new image is uploaded
//        if (imageFile != null && !imageFile.isEmpty()) {
//            ImageData imageData = uploadImage(imageFile);
//            post.setImage(imageData.getName());  // Set the new image name or URL
//        }
//
//        // Save the updated post
//        Post updatedPost = postRepository.save(post);
//        return mapToDTO(updatedPost);
//    }
//
//    // Delete a post by its ID
//    public void deletePost(Long id) {
//        postRepository.deleteById(id);
//    }
//
//    // Utility method to handle image upload and store it in ImageData table
//    private ImageData uploadImage(MultipartFile imageFile) throws IOException {
//        // Compress and store the image data
//        byte[] compressedImage = ImageUtils.compressImage(imageFile.getBytes());
//
//        // Create a new ImageData entity
//        ImageData imageData = new ImageData();
//        imageData.setName(imageFile.getOriginalFilename());
//        imageData.setType(imageFile.getContentType());
//        imageData.setImageData(compressedImage);  // Store the compressed image
//
//        // Save the image in the database using StorageRepository
//        return storageRepository.save(imageData);
//    }
//
//    // Map Post entity to PostDTO
//    private PostDTO mapToDTO(Post post) {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setId(post.getId());
//        postDTO.setTitle(post.getTitle());
//        postDTO.setContent(post.getContent());
//        postDTO.setStatus(post.getStatus());
//        postDTO.setImage(post.getImage());
//        postDTO.setCreatedAt(post.getCreatedAt());
//        postDTO.setUpdatedAt(post.getUpdatedAt());
//        return postDTO;
//    }
//}


import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToDTO(post);
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setStatus(postDTO.getStatus());
        post.setImage(postDTO.getImage());
        return mapToDTO(postRepository.save(post));
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setStatus(postDTO.getStatus());
        post.setImage(postDTO.getImage());
        return mapToDTO(postRepository.save(post));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setStatus(post.getStatus());
        postDTO.setImage(post.getImage());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        return postDTO;
    }

}
