package com.devsu.microservice.services;

import com.devsu.microservice.entities.ClientEntity;
import com.devsu.microservice.repositories.ClientRepository;
import com.devsu.microservice.dto.ClientDto;
import com.devsu.microservice.entities.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getClients() {
        List<ClientEntity> result = clientRepository.findAll();
        return result.stream().map(ClientDto::new).toList();
    }

    public Optional<ClientDto> getClientById(Long id) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        return clientEntity.map(ClientDto::new);
    }

    public ClientDto createClient(ClientDto clientDto) {
        PersonEntity personEntity = new PersonEntity(clientDto);
        ClientEntity clientEntity = new ClientEntity(clientDto.getPersonId(), personEntity, clientDto.getPwd(), clientDto.getStatus().name());

        return new ClientDto(clientRepository.save(clientEntity));
    }

    public Optional<ClientDto> updateClient(Long id, ClientDto clientDto) {
        Optional<ClientEntity> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            PersonEntity personEntity = new PersonEntity(clientDto);
            ClientEntity clientEntity = new ClientEntity(id, personEntity, clientDto.getPwd(), clientDto.getStatus().name());
            return Optional.of(new ClientDto(clientRepository.save(clientEntity)));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteClient(Long id) {
        Optional<ClientEntity> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<ClientDto> patchClient(Long id, ClientDto client) {
        Optional<ClientEntity> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            if(client.getFirstName() != null) {
                originalClient.get().getPerson().setFirstName(client.getFirstName());
            }
            if(client.getLastName() != null) {
                originalClient.get().getPerson().setLastName(client.getLastName());
            }
            if(client.getGender() != null) {
                originalClient.get().getPerson().setGender(client.getGender());
            }
            if(client.getPwd() != null) {
                originalClient.get().setPwd(client.getPwd());
            }
            if(client.getStatus() != null) {
                originalClient.get().setStatus(client.getStatus().name());
            }
            return Optional.of(new ClientDto(clientRepository.save(originalClient.get())));
        } else {
            return Optional.empty();
        }
    }
}
