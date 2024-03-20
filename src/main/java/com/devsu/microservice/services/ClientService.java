package com.devsu.microservice.services;

import com.devsu.microservice.dao.ClientRepository;
import com.devsu.microservice.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client client) {
        Optional<Client> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            client.setId(id);
            return Optional.of(clientRepository.save(client));
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

    public Optional<Client> patchClient(Long id, Client client) {
        Optional<Client> originalClient = clientRepository.findById(id);

        if(originalClient.isPresent()) {
            if(client.getPerson().getFirstName() != null) {
                originalClient.get().getPerson().setFirstName(client.getPerson().getFirstName());
            }
            if(client.getPerson().getLastName() != null) {
                originalClient.get().getPerson().setLastName(client.getPerson().getLastName());
            }
            if(client.getPerson().getGender() != null) {
                originalClient.get().getPerson().setGender(client.getPerson().getGender());
            }
            if(client.getPwd() != null) {
                originalClient.get().setPwd(client.getPwd());
            }
            if(client.getStatus() != null) {
                originalClient.get().setStatus(client.getStatus());
            }
            return Optional.of(clientRepository.save(originalClient.get()));
        } else {
            return Optional.empty();
        }
    }
}
