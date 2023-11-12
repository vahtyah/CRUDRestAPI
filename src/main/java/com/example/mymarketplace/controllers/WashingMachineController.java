package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.WashingMachine;
import com.example.mymarketplace.repositories.WashingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/washingmachines")
public class WashingMachineController {
    @Autowired
    private WashingMachineRepository washingMachineRepository;

    @GetMapping
    public String getAllWashingMachines(Model model) {
        List<WashingMachine> washingMachines = washingMachineRepository.findAll();
        washingMachines.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        System.out.println("WashingMachines: " + washingMachines);
        model.addAttribute("washingMachines", washingMachines);
        return "washingMachines";
    }

    @PostMapping("/add")
    public String addWashingMachine(@RequestParam String manufacturer, @RequestParam Integer tankVolume, @RequestParam String seller, @RequestParam String type, @RequestParam Integer cost, @RequestParam String name, Model model) {
        WashingMachine washingMachine = new WashingMachine(manufacturer, tankVolume, seller, type, cost, name);
        washingMachineRepository.save(washingMachine);
        return getAllWashingMachines(model);
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

    @GetMapping("/delete/{id}")
    public String deleteWashingMachine(@PathVariable Long id, Model model) {
        try {
            washingMachineRepository.deleteById(id);
            return getAllWashingMachines(model);
        } catch (Exception e) {
            return "WashingMachine not found";
        }
    }
}
