package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.*;
import com.example.mymarketplace.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TelephoneRepository telephoneRepository;
    @Autowired
    private WashingMachineRepository washingMachineRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping
    public String getAllProducts(Model model){
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("telephones", telephoneRepository.findAll());
        model.addAttribute("washingMachines", washingMachineRepository.findAll());

        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        carts.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));

        model.addAttribute("carts", carts);
        return "shoppingApp";
    }

    public List<Book> getAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }

    public List<Telephone> getAllTelephones(){
        return (List<Telephone>) telephoneRepository.findAll();
    }

    public List<WashingMachine> getAllWashingMachines(){
        return (List<WashingMachine>) washingMachineRepository.findAll();
    }

    public boolean IsAvailable(String name, String type, Integer amount){
        Warehouse warehouse = warehouseRepository.findByNameAndType(name, type);
        System.out.println(name+"------------------------------------------");
        if(warehouse == null) {
            System.out.println("Warehouse is null");
            return false;
        }
        System.out.println(warehouse.getAmount());
        return warehouse.getAmount() >= amount;
    }

    public List<Warehouse> getAllWarehouses(){
        return (List<Warehouse>) warehouseRepository.findAll();
    }


}
