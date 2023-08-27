package com.example.orm_sgbd.dtos;

import com.example.orm_sgbd.models.Movie;
import com.example.orm_sgbd.models.User;
import jakarta.validation.constraints.NotNull;

public record ReviewRecordDto(@NotNull User user, @NotNull Movie movie, String description, @NotNull double rating) {
}
