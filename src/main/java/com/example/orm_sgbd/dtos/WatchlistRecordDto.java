package com.example.orm_sgbd.dtos;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record WatchlistRecordDto(@NotNull UUID idUser, @NotNull UUID idMovie, @NotNull BigDecimal rating) {
}
