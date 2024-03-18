package org.romanzhula.bookstore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.romanzhula.bookstore.validations.BookCreateMarker;
import org.romanzhula.bookstore.validations.BookNameUnique;

@Data
@Builder //autogenerate inner class Builder - pattern
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @NotEmpty(message = "Book's title can not be empty!")
    @BookNameUnique(groups = BookCreateMarker.class)
    private String name;

    @NotEmpty(message = "Author can not be empty!")
    private String author;

    @NotEmpty(message = "Book's description can not be empty!")
    private String description;

    @DecimalMin(value = "50.00", message = "Price can not be less than 50.00 $")
    @NotNull(message = "Price can not be empty!")
    private Double price;
}
