package com.example.orm_sgbd.controllers;

import com.example.orm_sgbd.dtos.MovieRecordDto;
import com.example.orm_sgbd.models.Movie;
import com.example.orm_sgbd.repositories.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    private static final String MOVIE_NOT_FOUND = "Movie not found";

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody @Valid MovieRecordDto movieRecordDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieRecordDto, movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieRepository.save(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable("id") UUID id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MOVIE_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(movie.get());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable("id") UUID id, @RequestBody @Valid MovieRecordDto movieRecordDto) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MOVIE_NOT_FOUND);
        }
        Movie movieToBeUpdated = movie.get();
        BeanUtils.copyProperties(movieRecordDto, movieToBeUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(movieRepository.save(movieToBeUpdated));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") UUID id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MOVIE_NOT_FOUND);
        }
        movieRepository.delete(movie.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted");
    }
}
