package com.example.demo.service;


import com.example.demo.entity.ImageData;
import com.example.demo.repository.StorageRepository;
import com.example.demo.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

//@Service
//public class StorageService {
//
//    @Autowired
//    private StorageRepository repository;
//
//    // Upload image (store as byte array)
//    public String uploadImage(MultipartFile file) throws IOException {
//        ImageData imageData = new ImageData();
//        imageData.setName(file.getOriginalFilename());
//        imageData.setType(file.getContentType());
//        imageData.setImageData(file.getBytes());  // Store image data as byte array
//
//        // Save to database
//        repository.save(imageData);
//
//        return "File uploaded successfully: " + file.getOriginalFilename();
//    }
//
//    // Download image (retrieve as byte array)
//    public byte[] downloadImage(String fileName) throws IOException {
//        // Retrieve the image from the database by name
//        Optional<ImageData> imageDataOptional = repository.findByName(fileName);
//
//        if (imageDataOptional.isPresent()) {
//            ImageData imageData = imageDataOptional.get();
//            return imageData.getImageData();  // Return image data as byte array
//        } else {
//            throw new FileNotFoundException("Image not found: " + fileName);
//        }
//    }
//
//    // Delete image (delete by name)
//    @Transactional
//    public boolean deleteImage(String fileName) {
//        Optional<ImageData> imageDataOptional = repository.findByName(fileName);
//
//        if (imageDataOptional.isPresent()) {
//            repository.delete(imageDataOptional.get());  // Delete the image from the database
//            return true;  // Return true if the image was deleted
//        } else {
//            return false;  // Return false if the image was not found
//        }
//    }
//}



@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;


    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    // New delete method
    @Transactional
    public boolean deleteImage(String fileName) {
        Optional<ImageData> imageDataOptional = repository.findByName(fileName);
        if (imageDataOptional.isPresent()) {
            repository.delete(imageDataOptional.get());  // Delete the image from the database
            return true;  // Return true if the image was deleted
        }
        return false;  // Return false if the image was not found
    }
}