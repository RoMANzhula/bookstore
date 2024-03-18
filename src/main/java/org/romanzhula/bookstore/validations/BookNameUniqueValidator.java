package org.romanzhula.bookstore.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.romanzhula.bookstore.repositories.BookRepository;

@RequiredArgsConstructor
public class BookNameUniqueValidator implements ConstraintValidator<BookNameUnique, String> {

    private final BookRepository bookRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return bookRepository.findByName(value).isEmpty();
    }
}
