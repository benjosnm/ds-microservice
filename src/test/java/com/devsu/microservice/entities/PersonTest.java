package com.devsu.microservice.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonTest {
    @Autowired
    private Validator validator;

    @Test
    void invalidPerson() {
        Person person = new Person(" ", null);

        Set<ConstraintViolation<Person>> validationError = validator.validate(person);
        Iterator<ConstraintViolation<Person>> iterator = validationError.iterator();

        assertAll(
                () -> assertFalse(validationError.isEmpty()),
                () -> assertEquals(2, validationError.size()),
                () -> assertEquals("First name is required.", iterator.next().getMessage()),
                () -> assertEquals("Last name is required.", iterator.next().getMessage())
        );
    }

}