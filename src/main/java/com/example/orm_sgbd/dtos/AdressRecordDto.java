package com.example.orm_sgbd.dtos;
import jakarta.validation.constraints.NotBlank;

public record AdressRecordDto(@NotBlank String streetName , @NotBlank int adressNumber, String complement, 
		@NotBlank String district,  @NotBlank String city,  @NotBlank String country) {
}