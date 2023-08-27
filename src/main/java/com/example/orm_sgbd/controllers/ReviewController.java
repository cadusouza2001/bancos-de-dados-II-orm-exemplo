package com.example.orm_sgbd.controllers;

import com.example.orm_sgbd.dtos.ReviewRecordDto;
import com.example.orm_sgbd.models.Review;
import com.example.orm_sgbd.repositories.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    private static final String REVIEW_NOT_FOUND = "Review not found";

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRecordDto reviewRecordDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewRecordDto, review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewRepository.save(review));
    }

    @GetMapping
    public ResponseEntity<Iterable<Review>> getAllReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getReviewById(@PathVariable("id") UUID id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REVIEW_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable("id") UUID id, @RequestBody ReviewRecordDto reviewRecordDto) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REVIEW_NOT_FOUND);
        }
        BeanUtils.copyProperties(reviewRecordDto, review);
        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.save(review));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable("id") UUID id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REVIEW_NOT_FOUND);
        }
        reviewRepository.delete(review);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted");
    }

    @GetMapping(path = "/by-user/{id}")
    public ResponseEntity<Object> getReviewsByUserId(@PathVariable("id") UUID id) {
        Iterable<Review> reviews = reviewRepository.findByUserIdUser(id);
        if (reviews == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REVIEW_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping(path = "/by-movie/{id}")
    public ResponseEntity<Object> getReviewsByMovieId(@PathVariable("id") UUID id) {
        Iterable<Review> reviews = reviewRepository.findByMovieIdMovie(id);
        if (reviews == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REVIEW_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping(path = "/by-user-movie/{id_user}/{id_movie}")
    public ResponseEntity<Object> getReviewsByUserIdAndMovieId(@PathVariable("id_user") UUID idUser, @PathVariable("id_movie") UUID idMovie) {
        Iterable<Review> reviews = reviewRepository.findByUserIdUserAndMovieIdMovie(idUser, idMovie);
        if (reviews == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(REVIEW_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }


}
