package com.devsu.microservice.controllers;

import com.devsu.microservice.dto.ClientDto;
import com.devsu.microservice.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.of(clientService.getClientById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto client) {
        ClientDto newClient = clientService.createClient(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newClient.getPersonId())
                .toUri();
        return ResponseEntity.created(location).body(newClient);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody ClientDto client) {
        return ResponseEntity.of(clientService.updateClient(id, client));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.deleteClient(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<ClientDto> patchClient(@PathVariable Long id, @RequestBody ClientDto client) {
        return ResponseEntity.of(clientService.patchClient(id, client));
    }
}
