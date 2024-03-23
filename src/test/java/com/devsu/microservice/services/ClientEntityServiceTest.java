package com.devsu.microservice.services;

import com.devsu.microservice.entities.ClientEntity;
import com.devsu.microservice.repositories.ClientRepository;
import com.devsu.microservice.dto.ClientDto;
import com.devsu.microservice.entities.PersonEntity;
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
class ClientEntityServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void getClients() {
        List<ClientEntity> clientEntities = generateClientsTestData(5);
        when(clientRepository.findAll()).thenReturn(clientEntities);

        List<ClientDto> result = clientService.getClients();

        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(5, result.size())
        );
    }

    @Test
    void getClient() {
        ClientEntity clientEntity = generateClientsTestData(1).get(0);
        when(clientRepository.findById(clientEntity.getId())).thenReturn(java.util.Optional.of(clientEntity));

        ClientDto result = clientService.getClientById(clientEntity.getId()).get();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, clientEntity.getId())
        );
    }

    @Test
    void createClient() {
        ClientEntity clientEntity = generateClientsTestData(1).get(0);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        ClientDto result = clientService.createClient(new ClientDto(clientEntity));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId())
        );
    }

    @Test
    void updateClient() {
        ClientEntity clientEntity = generateClientsTestData(1).get(0);
        when(clientRepository.findById(clientEntity.getId())).thenReturn(java.util.Optional.of(clientEntity));

        clientEntity.getPerson().setFirstName("Juan-updated");
        clientEntity.getPerson().setLastName("Perez-updated");
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        Optional<ClientDto> result = clientService.updateClient(clientEntity.getId(), new ClientDto(clientEntity));

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
        ClientEntity clientEntity = generateClientsTestData(1).get(0);
        when(clientRepository.findById(clientEntity.getId())).thenReturn(java.util.Optional.of(clientEntity));

        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName("Juan-patched");
        clientDto.setPwd("patched-pwd");

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        Optional<ClientDto> result = clientService.patchClient(clientEntity.getId(), clientDto);

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
        ClientEntity clientEntity = generateClientsTestData(1).get(0);
        when(clientRepository.findById(clientEntity.getId())).thenReturn(java.util.Optional.of(clientEntity));

        boolean result = clientService.deleteClient(clientEntity.getId());

        assertTrue(result);
    }

    private List<ClientEntity> generateClientsTestData(int size) {
        List<ClientEntity> result = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            result.add(new ClientEntity(
                    (long) i,
                    new PersonEntity(
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