package com.devsu.microservice.repositories;

import com.devsu.microservice.entities.ClientEntity;
import com.devsu.microservice.entities.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class ClientEntityRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    void createClient() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date birthDate = dateFormat.parse("1980-10-15");
        PersonEntity personEntity = new PersonEntity(null, "John", "Doe", "M", birthDate, "123 Main St", "1234567890");
        ClientEntity clientEntity = new ClientEntity(null, personEntity, "123456", "ACTIVE");

        ClientEntity saved = clientRepository.save(clientEntity);

        assertAll(
                () -> assertNotNull(saved),
                () -> assertNotNull(saved.getId()),
                () -> assertNotNull(saved.getPerson()),
                () -> assertNotNull(saved.getPwd()),
                () -> assertNotNull(saved.getStatus())
        );
    }

    @Test
    void getClients() {
        List<ClientEntity> all = clientRepository.findAll();

        assertAll(
                () -> assertNotNull(all),
                () -> assertFalse(all.isEmpty())
        );
    }

    @Test
    void getClient() {
        ClientEntity clientEntity = clientRepository.findById(1L).orElse(null);

        assertAll(
                () -> assertNotNull(clientEntity),
                () -> assertNotNull(clientEntity.getId()),
                () -> assertEquals(1L, clientEntity.getId()),
                () -> assertNotNull(clientEntity.getPerson()),
                () -> assertEquals("Jhon", clientEntity.getPerson().getFirstName()),
                () -> assertEquals("Potter", clientEntity.getPerson().getLastName()),
                () -> assertNotNull(clientEntity.getPwd()),
                () -> assertNotNull(clientEntity.getStatus())
        );
    }

    @Test
    void updateClient() {
        ClientEntity clientEntity = clientRepository.findById(1L).orElse(null);
        assertNotNull(clientEntity);

        clientEntity.setStatus("INACTIVE");
        ClientEntity updated = clientRepository.save(clientEntity);

        assertAll(
                () -> assertNotNull(updated),
                () -> assertNotNull(updated.getId()),
                () -> assertEquals(1L, updated.getId()),
                () -> assertNotNull(updated.getPerson()),
                () -> assertNotNull(updated.getPwd()),
                () -> assertNotNull(updated.getStatus()),
                () -> assertEquals("INACTIVE", updated.getStatus())
        );
    }

    @Test
    void deleteClient() {
        clientRepository.deleteById(1L);
        assertFalse(clientRepository.existsById(1L));
    }
}