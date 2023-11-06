package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.WashingMachine;
import com.example.mymarketplace.repositories.WashingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/washingmachines")
public class WashingMachineController {
    @Autowired
    private WashingMachineRepository washingMachineRepository;

    @GetMapping
    public List<WashingMachine> getAllWashingMachines() {
        return washingMachineRepository.findAll();
    }

    @GetMapping("/{id}")
    public WashingMachine getWashingMachineById(@PathVariable Long id) {
        return washingMachineRepository.findById(id).get();
    }

    @PostMapping
    public WashingMachine createWashingMachine(@RequestBody WashingMachine washingMachine) {
        return washingMachineRepository.save(washingMachine);
    }

    @PutMapping("/{id}")
    public WashingMachine updateWashingMachine(@PathVariable Long id, @RequestBody WashingMachine washingMachine) {
        WashingMachine washingMachineFromDb = washingMachineRepository.findById(id).get();
        washingMachineFromDb.setCost(washingMachine.getCost());
        washingMachineFromDb.setName(washingMachine.getName());
        washingMachineFromDb.setSeller(washingMachine.getSeller());
        washingMachineFromDb.setType(washingMachine.getType());
        return washingMachineRepository.save(washingMachineFromDb);
    }

    @DeleteMapping("/{id}")
    public String deleteWashingMachine(@PathVariable Long id) {
        try {
            washingMachineRepository.deleteById(id);
            return "WashingMachine deleted";
        } catch (Exception e) {
            return "WashingMachine not found";
        }
    }
}
