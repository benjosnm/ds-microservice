package com.devsu.microservice.repositories;

import com.devsu.microservice.dao.ClientRepository;
import com.devsu.microservice.entities.Client;
import com.devsu.microservice.entities.Person;
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
class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    void createClient() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date birthDate = dateFormat.parse("1980-10-15");
        Person person = new Person(null, "John", "Doe", "M", birthDate, "123 Main St", "1234567890");
        Client client = new Client(null, person, "123456", "ACTIVE");

        Client saved = clientRepository.save(client);

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
        List<Client> all = clientRepository.findAll();

        assertAll(
                () -> assertNotNull(all),
                () -> assertFalse(all.isEmpty())
        );
    }

    @Test
    void getClient() {
        Client client = clientRepository.findById(1L).orElse(null);

        assertAll(
                () -> assertNotNull(client),
                () -> assertNotNull(client.getId()),
                () -> assertEquals(1L, client.getId()),
                () -> assertNotNull(client.getPerson()),
                () -> assertEquals("Jhon", client.getPerson().getFirstName()),
                () -> assertEquals("Potter", client.getPerson().getLastName()),
                () -> assertNotNull(client.getPwd()),
                () -> assertNotNull(client.getStatus())
        );
    }

    @Test
    void updateClient() {
        Client client = clientRepository.findById(1L).orElse(null);
        assertNotNull(client);

        client.setStatus("INACTIVE");
        Client updated = clientRepository.save(client);

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