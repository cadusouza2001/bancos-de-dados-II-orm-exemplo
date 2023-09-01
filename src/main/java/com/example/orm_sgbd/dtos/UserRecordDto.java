package com.example.orm_sgbd.dtos;

import com.example.orm_sgbd.models.Adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank String username, @NotNull Adress adress) {
}
