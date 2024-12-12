package com.example.demo.controller;

//import com.example.demo.dto.PostDTO;
//import com.example.demo.service.PostService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/posts")
//@RequiredArgsConstructor
//public class PostController {
//
//    private final PostService postService;
//
//    // Create a new post with an image upload
//    @PostMapping("/create")
//    public ResponseEntity<PostDTO> createPost(@RequestParam("title") String title,
//                                              @RequestParam("content") String content,
//                                              @RequestParam("status") String status,
//                                              @RequestParam("image") MultipartFile imageFile) throws IOException {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setTitle(title);
//        postDTO.setContent(content);
//        postDTO.setStatus(status);
//
//        PostDTO createdPost = postService.createPost(postDTO, imageFile);
//        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
//    }
//
//    // Update an existing post with an optional new image upload
//    @PutMapping("/update/{id}")
//    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id,
//                                              @RequestParam("title") String title,
//                                              @RequestParam("content") String content,
//                                              @RequestParam("status") String status,
//                                              @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setTitle(title);
//        postDTO.setContent(content);
//        postDTO.setStatus(status);
//
//        PostDTO updatedPost = postService.updatePost(id, postDTO, imageFile);
//        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
//    }
//
//    // Get a specific post by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
//        PostDTO postDTO = postService.getPostById(id);
//        return new ResponseEntity<>(postDTO, HttpStatus.OK);
//    }
//
//    // Get all posts
//    @GetMapping("/all")
//    public ResponseEntity<List<PostDTO>> getAllPosts() {
//        List<PostDTO> posts = postService.getAllPosts();
//        return new ResponseEntity<>(posts, HttpStatus.OK);
//    }
//
//    // Delete a post by ID
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
//        postService.deletePost(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}


import com.example.demo.dto.PostDTO;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Get all posts
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Get a post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // Create a new post
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // Update an existing post
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }

    // Delete a post by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
