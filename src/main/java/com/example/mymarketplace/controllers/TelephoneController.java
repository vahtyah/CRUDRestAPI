package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Telephone;
import com.example.mymarketplace.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/telephones")
public class TelephoneController {
    @Autowired
    private TelephoneRepository telephoneRepository;

    @GetMapping
    public String getAllTelephones(Model model) {
        List<Telephone> telephones = telephoneRepository.findAll();
        telephones.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        model.addAttribute("telephones", telephones);
        return "telephones";
    }

    @PostMapping("/add")
    public String addTelephone(@RequestParam String manufacturer, @RequestParam Integer batteryCapacity, @RequestParam String seller, @RequestParam String type, @RequestParam Integer cost, @RequestParam String name, Model model) {
        Telephone telephone = new Telephone(manufacturer, batteryCapacity, seller, type, cost, name);
        telephoneRepository.save(telephone);
        return getAllTelephones(model);
    }

    @PutMapping("/{id}")
    public Telephone updateTelephone(@PathVariable Long id, @RequestBody Telephone telephone) {
        Telephone telephoneFromDb = telephoneRepository.findById(id).get();
        telephoneFromDb.setCost(telephone.getCost());
        telephoneFromDb.setName(telephone.getName());
        telephoneFromDb.setSeller(telephone.getSeller());
        telephoneFromDb.setType(telephone.getType());
        return telephoneRepository.save(telephoneFromDb);
    }

    @GetMapping("/delete/{id}")
    public String deleteTelephone(@PathVariable Long id, Model model) {
        try {
            telephoneRepository.deleteById(id);
            return getAllTelephones(model);
        } catch (Exception e) {
            return "Telephone not found";
        }
    }

}
