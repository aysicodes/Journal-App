package com.example.demo.repository;

import com.example.demo.entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {

}

