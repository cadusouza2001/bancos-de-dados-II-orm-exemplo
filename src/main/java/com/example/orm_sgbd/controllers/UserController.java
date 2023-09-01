package com.example.orm_sgbd.controllers;

import com.example.orm_sgbd.dtos.UserRecordDto;
import com.example.orm_sgbd.models.Review;
import com.example.orm_sgbd.models.User;
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

    private static final String USER_NOT_FOUND = "User not found";

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRecordDto userRecordDto) {
        User user = new User();
        BeanUtils.copyProperties(userRecordDto, user);
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
        BeanUtils.copyProperties(userRecordDto, user);
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
    
    @GetMapping(path = "/by-adress/{id}")
    public ResponseEntity<Object> getUsersByAdressId(@PathVariable("id") UUID id) {
        Iterable<Review> users = userRepository.findByAdressIdAdress(id);
        if (users == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
