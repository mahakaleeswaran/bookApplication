package com.demo.bookapplication.dto;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String name;
    private String author;
    private String genre;
    @Positive(message = "Price must be a positive value")
    private Double price;
}
