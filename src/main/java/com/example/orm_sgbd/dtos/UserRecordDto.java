package com.example.orm_sgbd.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String username) {
}
