package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Telephone;
import com.example.mymarketplace.repositories.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/telephones")
public class TelephoneController {
    @Autowired
    private TelephoneRepository telephoneRepository;

    @GetMapping
    public String getAllTelephones(Model model) {
        List<Telephone> telephones = telephoneRepository.findAll();
        model.addAttribute("telephones", telephones);
        return "products";
    }

    @GetMapping("/{id}")
    public Telephone getTelephoneById(@PathVariable Long id) {
        return telephoneRepository.findById(id).get();
    }

    @PostMapping
    public Telephone createTelephone(@RequestBody Telephone telephone) {
        return telephoneRepository.save(telephone);
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

    @DeleteMapping("/{id}")
    public String deleteTelephone(@PathVariable Long id) {
        try {
            telephoneRepository.deleteById(id);
            return "Telephone deleted";
        } catch (Exception e) {
            return "Telephone not found";
        }
    }

}
