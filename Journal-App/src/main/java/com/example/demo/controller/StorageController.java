package com.example.demo.controller;

import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@RestController
//@RequestMapping("/images")
//public class StorageController {
//
//    @Autowired
//    private StorageService storageService;
//
//    // Upload an image (POST request)
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//        try {
//            String message = storageService.uploadImage(file);
//            return ResponseEntity.ok(message);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//        }
//    }
//
//    // Download an image (GET request)
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
//        try {
//            byte[] imageData = storageService.downloadImage(fileName);
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType("image/jpeg")) // Set content type based on your image type
//                    .body(imageData);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    // Delete an image (DELETE request)
//    @DeleteMapping("/delete/{fileName}")
//    public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
//        boolean isDeleted = storageService.deleteImage(fileName);
//
//        if (isDeleted) {
//            return ResponseEntity.ok("Image deleted successfully: " + fileName);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found: " + fileName);
//        }
//    }
//}



@RestController
@RequestMapping("/image")
public class StorageController {
    @Autowired
    private StorageService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = service.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .body(imageData);
    }
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
        boolean isDeleted = service.deleteImage(fileName);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully: " + fileName);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found: " + fileName);
        }
    }
}
