package com.devsu.microservice.services;

import com.devsu.microservice.dao.ClientRepository;
import com.devsu.microservice.dto.ClientDto;
import com.devsu.microservice.entities.Client;
import com.devsu.microservice.entities.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void getClients() {
        List<Client> clients = generateClientsTestData(5);
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> result = clientService.getClients();

        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(5, result.size())
        );
    }

    @Test
    void getClient() {
        Client client = generateClientsTestData(1).get(0);
        when(clientRepository.findById(client.getId())).thenReturn(java.util.Optional.of(client));

        ClientDto result = clientService.getClientById(client.getId()).get();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, client.getId())
        );
    }

    @Test
    void createClient() {
        Client client = generateClientsTestData(1).get(0);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto result = clientService.createClient(new ClientDto(client));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId())
        );
    }

    @Test
    void updateClient() {
        Client client = generateClientsTestData(1).get(0);
        when(clientRepository.findById(client.getId())).thenReturn(java.util.Optional.of(client));

        client.getPerson().setFirstName("Juan-updated");
        client.getPerson().setLastName("Perez-updated");
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Optional<ClientDto> result = clientService.updateClient(client.getId(), new ClientDto(client));

        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(1L, result.get().getId()),
                () -> assertEquals("Juan-updated", result.get().getFirstName()),
                () -> assertEquals("Perez-updated", result.get().getLastName())
        );
    }

    @Test
    void patchClient() {
        Client client = generateClientsTestData(1).get(0);
        when(clientRepository.findById(client.getId())).thenReturn(java.util.Optional.of(client));

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName("Juan-patched");
        clientDto.setPwd("patched-pwd");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Optional<ClientDto> result = clientService.patchClient(client.getId(), clientDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(1L, result.get().getId()),
                () -> assertEquals("Juan-patched", result.get().getFirstName()),
                () -> assertEquals("patched-pwd", result.get().getPwd())
        );
    }

    @Test
    void deleteClient() {
        Client client = generateClientsTestData(1).get(0);
        when(clientRepository.findById(client.getId())).thenReturn(java.util.Optional.of(client));

        boolean result = clientService.deleteClient(client.getId());

        assertTrue(result);
    }

    private List<Client> generateClientsTestData(int size) {
        List<Client> result = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            result.add(new Client(
                    (long) i,
                    new Person(
                            (long) i,
                            "Juan" + i,
                            "Perez" + i,
                            "M",
                            new Date(),
                            "123 Main St",
                            "1234567890"
                    ),
                    "123456",
                    "ACTIVE"
            ));
        }

        return result;
    }

}