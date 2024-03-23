package com.devsu.microservice.services;

import com.devsu.microservice.dao.ClientRepository;
import com.devsu.microservice.dto.ClientDto;
import com.devsu.microservice.entities.Client;
import com.devsu.microservice.entities.Person;
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
        List<Client> result = clientRepository.findAll();
        return result.stream().map(ClientDto::new).toList();
    }

    public Optional<ClientDto> getClientById(Long id) {
        Optional<Client> clientEntity = clientRepository.findById(id);
        return clientEntity.map(ClientDto::new);
    }

    public ClientDto createClient(ClientDto clientDto) {
        Person person = new Person(clientDto);
        Client client = new Client(clientDto.getPersonId(), person, clientDto.getPwd(), clientDto.getStatus().name());

        return new ClientDto(clientRepository.save(client));
    }

    public Optional<ClientDto> updateClient(Long id, ClientDto clientDto) {
        Optional<Client> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            Person person = new Person(clientDto);
            Client client = new Client(id, person, clientDto.getPwd(), clientDto.getStatus().name());
            return Optional.of(new ClientDto(clientRepository.save(client)));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteClient(Long id) {
        Optional<Client> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<ClientDto> patchClient(Long id, ClientDto client) {
        Optional<Client> originalClient = clientRepository.findById(id);

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
