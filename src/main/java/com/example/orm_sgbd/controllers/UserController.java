package com.example.orm_sgbd.controllers;

import com.example.orm_sgbd.dtos.UserRecordDto;
import com.example.orm_sgbd.models.ContactInfo;
import com.example.orm_sgbd.models.Movie;
import com.example.orm_sgbd.models.User;
import com.example.orm_sgbd.repositories.MovieRepository;
import com.example.orm_sgbd.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    private static final String USER_NOT_FOUND = "User not found";
    private static final String MOVIE_NOT_FOUND = "Movie not found";

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRecordDto userRecordDto) {

        User user = new User();
        BeanUtils.copyProperties(userRecordDto, user);

        user.getContactInfo().setUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }

        ContactInfo contactInfo = user.getContactInfo();

        BeanUtils.copyProperties(userRecordDto, user);

        user.setContactInfo(contactInfo);

        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @GetMapping(path = "/by-contact/{id}")
    public ResponseEntity<Object> getUserB (@PathVariable("id") UUID id) {
        Iterable<User> users = userRepository.findByContactInfoIdContactInfo(id);
        if (users == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping(path = "/{id}/watchlist/{idMovie}")
    public ResponseEntity<Object> addMovieToWatchlist(@PathVariable("id") UUID id, @PathVariable("idMovie") UUID idMovie) {
        User user = userRepository.findById(id).orElse(null);
        Movie movie = movieRepository.findById(idMovie).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MOVIE_NOT_FOUND);
        }
        user.getWatchlist().add(movie);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Movie added to watchlist");
    }

    @GetMapping(path = "/{id}/watchlist")
    public ResponseEntity<Object> getWatchlist(@PathVariable("id") UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.getWatchlist());
    }

    @DeleteMapping(path = "/{id}/watchlist/{idMovie}")
    public ResponseEntity<Object> removeMovieFromWatchlist(@PathVariable("id") UUID id, @PathVariable("idMovie") UUID idMovie) {
        User user = userRepository.findById(id).orElse(null);
        Movie movie = movieRepository.findById(idMovie).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MOVIE_NOT_FOUND);
        }
        user.getWatchlist().remove(movie);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Movie removed from watchlist");
    }

}
