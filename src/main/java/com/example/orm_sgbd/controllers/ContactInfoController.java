package com.example.orm_sgbd.controllers;

import com.example.orm_sgbd.dtos.ContactInfoRecordDto;

import com.example.orm_sgbd.models.ContactInfo;
import com.example.orm_sgbd.repositories.ContactInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("contact-info")
public class ContactInfoController {
    @Autowired
    private ContactInfoRepository contactInfoRepository;

    private static final String CONTACT_NOT_FOUND = "Contact not found";

    @GetMapping
    public ResponseEntity<List<ContactInfo>> getAllContactInfos() {


        return ResponseEntity.status(HttpStatus.OK).body(contactInfoRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getContactInfoById(@PathVariable("id") UUID id) {
        Optional<ContactInfo> contactInfo = contactInfoRepository.findById(id);
        if (contactInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CONTACT_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactInfo.get());
    }

    @GetMapping(path = "/by-user/{id}")
    public ResponseEntity<Object> getContactInfoByUserId(@PathVariable("id") UUID id) {
        Iterable<ContactInfo> contactInfo = contactInfoRepository.findByUserIdUser(id);
        if (contactInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CONTACT_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(contactInfo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateContactInfo(@PathVariable("id") UUID id, @RequestBody ContactInfoRecordDto contactInfoRecordDto) {
        Optional<ContactInfo> contactInfo = contactInfoRepository.findById(id);
        if (contactInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CONTACT_NOT_FOUND);
        }
        ContactInfo contactInfoToBeUpdated = contactInfo.get();
        BeanUtils.copyProperties(contactInfoRecordDto, contactInfoToBeUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(contactInfoRepository.save(contactInfoToBeUpdated));
    }

}