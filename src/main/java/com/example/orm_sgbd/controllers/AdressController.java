package com.example.orm_sgbd.controllers;

import com.example.orm_sgbd.dtos.AdressRecordDto;
import com.example.orm_sgbd.models.Adress;
import com.example.orm_sgbd.repositories.AdressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("adresses")
public class AdressController {
    @Autowired
    private AdressRepository adressRepository;

    private static final String ADRESS_NOT_FOUND = "Adress not found";

    @PostMapping
    public ResponseEntity<Adress> createAdress(@RequestBody AdressRecordDto adressRecordDto) {
        var adress = new Adress();
        BeanUtils.copyProperties(adressRecordDto, adress);
        return ResponseEntity.status(HttpStatus.CREATED).body(adressRepository.save(adress));
    }

    @GetMapping
    public ResponseEntity<List<Adress>> getAllAdresses() {
        return ResponseEntity.status(HttpStatus.OK).body(adressRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getAdressById(@PathVariable("id") UUID id) {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ADRESS_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(adress.get());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateAdress(@PathVariable("id") UUID id, @RequestBody AdressRecordDto adressRecordDto) {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ADRESS_NOT_FOUND);
        }
        Adress adressToBeUpdated = adress.get();
        BeanUtils.copyProperties(adressRecordDto, adressToBeUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(adressRepository.save(adressToBeUpdated));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteAdress(@PathVariable("id") UUID id) {
        Optional<Adress> adress = adressRepository.findById(id);
        if (adress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ADRESS_NOT_FOUND);
        }
        adressRepository.delete(adress.get());
        return ResponseEntity.status(HttpStatus.OK).body("Adress deleted");
    }
}