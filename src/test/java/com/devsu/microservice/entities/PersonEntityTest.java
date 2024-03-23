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
class PersonEntityTest {
    @Autowired
    private Validator validator;

    @Test
    void invalidPerson() {
        PersonEntity personEntity = new PersonEntity();

        Set<ConstraintViolation<PersonEntity>> validationError = validator.validate(personEntity);
        Iterator<ConstraintViolation<PersonEntity>> iterator = validationError.iterator();

        assertAll(
                () -> assertFalse(validationError.isEmpty()),
                () -> assertEquals(2, validationError.size()),
                () -> assertEquals("First name is required.", iterator.next().getMessage()),
                () -> assertEquals("Last name is required.", iterator.next().getMessage())
        );
    }

}