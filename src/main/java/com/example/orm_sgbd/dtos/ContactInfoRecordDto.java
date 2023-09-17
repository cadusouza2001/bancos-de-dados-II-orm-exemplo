package com.example.orm_sgbd.dtos;
import jakarta.validation.constraints.NotBlank;

public record ContactInfoRecordDto(@NotBlank String streetName , @NotBlank String addressNumber, String complement,
								   @NotBlank String district, @NotBlank String city, @NotBlank String country, String phoneNumber) {
}