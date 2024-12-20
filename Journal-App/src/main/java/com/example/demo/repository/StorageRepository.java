package com.example.demo.repository;

import com.example.demo.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<ImageData, Long> {
    @Query(value = "SELECT * FROM ImageData WHERE name = :fileName", nativeQuery = true)
    Optional<ImageData> findByName(@Param("fileName") String fileName);
}