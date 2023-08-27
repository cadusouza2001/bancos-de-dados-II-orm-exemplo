package com.example.orm_sgbd.repositories;

import com.example.orm_sgbd.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    Iterable<Review> findByUserIdUser(UUID id);
    Iterable<Review> findByMovieIdMovie(UUID id);

    Iterable<Review> findByUserIdUserAndMovieIdMovie(UUID idUser, UUID idMovie);
}
