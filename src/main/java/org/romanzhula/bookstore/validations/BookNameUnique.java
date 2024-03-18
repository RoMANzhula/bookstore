package org.romanzhula.bookstore.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookNameUniqueValidator.class)
@Target(ElementType.FIELD) // for using with variables(String, Long etc.) (TYPE - our annotation using for all class)
@Retention(RetentionPolicy.RUNTIME) //only runtime - we can use our annotation
public @interface BookNameUnique {

    String message() default "Book with this name existed yet!";

    Class<?>[] groups() default {}; //for places where we'll be use annotation

    Class<? extends Payload>[] payload() default {}; //for different useful markers
}
