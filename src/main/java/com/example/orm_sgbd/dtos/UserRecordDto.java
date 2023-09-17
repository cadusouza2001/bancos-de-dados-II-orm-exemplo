package com.example.orm_sgbd.dtos;

import com.example.orm_sgbd.models.ContactInfo;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String username, ContactInfo contactInfo) {

    public String getUsername() {
        return this.username;
    }
    public ContactInfo getContactInfo() {
        return this.contactInfo;
    }
}
