package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Client;
import com.example.mymarketplace.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id).get();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client clientFromDb = clientRepository.findById(id).get();
        clientFromDb.setEmail(client.getEmail());
        clientFromDb.setLogin(client.getLogin());
        clientFromDb.setName(client.getName());
        clientFromDb.setPassword(client.getPassword());
        return clientRepository.save(clientFromDb);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {
        try {
            clientRepository.deleteById(id);
            return "Client deleted";
        } catch (Exception e) {
            return "Client not found";
        }
    }
}
