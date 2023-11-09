package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.*;
import com.example.mymarketplace.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TelephoneRepository telephoneRepository;
    @Autowired
    private WashingMachineRepository washingMachineRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ShopController shopController;

    @GetMapping
    public String getAllCarts(Model model){
        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        carts.sort(Comparator.comparing(Cart::getId));
        model.addAttribute("carts", carts);
        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam(value = "type", defaultValue = "Book") String type, Model model){
        if(cartRepository.findByIdOfProductAndType(id, type) == null){
            switch (type) {
                case "Book" -> {
                    Book book = bookRepository.findById(id).get();
                    if(!shopController.IsAvailable(book.getName(), type, 1)) {
                        System.out.println("Product is not available.");
                        model.addAttribute("errorMessage", "Product is not available.");
                        return shopController.getAllProducts(model);
                    }
                    Cart cart = new Cart(id, book.getName(), book.getCost(), 1, type);
                    cartRepository.save(cart);
                }
                case "Telephone" -> {
                    Telephone telephone = telephoneRepository.findById(id).get();
                    if (!shopController.IsAvailable(telephone.getName(), type, 1)) {
                        System.out.println("Product is not available.");
                        model.addAttribute("errorMessage", "Product is not available.");
                        return shopController.getAllProducts(model);
                    }
                    Cart cart = new Cart(id, telephone.getName(), telephone.getCost(), 1, type);
                    cartRepository.save(cart);
                }
                case "WashingMachine" -> {
                    WashingMachine washingMachine = washingMachineRepository.findById(id).get();
                    if (!shopController.IsAvailable(washingMachine.getName(), type, 1)) {
                        System.out.println("Product is not available.");
                        model.addAttribute("errorMessage", "Product is not available.");
                        return shopController.getAllProducts(model);
                    }
                    Cart cart = new Cart(id, washingMachine.getName(), washingMachine.getCost(), 1, type);
                    cartRepository.save(cart);
                }
            }
        }
        else{
            Cart cart = cartRepository.findByIdOfProductAndType(id, type);
            var cost = cart.getSumCost()/cart.getAmount();
            cart.setAmount(cart.getAmount() + 1);
            cart.setSumCost(cost * cart.getAmount());
            cartRepository.save(cart);
        }
        return shopController.getAllProducts(model);
    }

    @GetMapping("/deleteFromCart/{id}")
    public String deleteFromCart(@PathVariable Long id, Model model){
        Cart cart = cartRepository.findById(id).get();
        cartRepository.delete(cart);
        return shopController.getAllProducts(model);
    }

    @GetMapping("/deleteAll")
    public String deleteAll(Model model){
        List<Warehouse> warehouses = (List<Warehouse>) shopController.getAllWarehouses();
        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        Dictionary<Warehouse, Integer> productToDelete = new Hashtable<>();
        AtomicBoolean isAvailable = new AtomicBoolean(true);
        carts.forEach(cart -> {
            if(shopController.IsAvailable(cart.getName(), cart.getType(), cart.getAmount()))
                productToDelete.put(warehouseRepository.findByNameAndType(cart.getName(), cart.getType()), cart.getAmount());
            else isAvailable.set(false);
        });
        if(!isAvailable.get()){
            model.addAttribute("errorMessage", "Product is not available.");
            return shopController.getAllProducts(model);
        }
        ((Hashtable<Warehouse, Integer>) productToDelete).forEach((warehouse, amount) -> {
            warehouse.setAmount(warehouse.getAmount() - amount);
            warehouseRepository.save(warehouse);
        });
        cartRepository.deleteAll();
        return shopController.getAllProducts(model);
    }

    @PostMapping("/updateAmount")
    public String updateAmount(@RequestParam Long id, @RequestParam String action, Model model){
        Cart cart = cartRepository.findById(id).get();
        if(action.equals("decrease")){
            if(cart.getAmount() > 1){
                var cost = cart.getSumCost()/cart.getAmount();
                cart.setAmount(cart.getAmount() - 1);
                cart.setSumCost(cost * cart.getAmount());
                cartRepository.save(cart);
            }
        }
        else if(action.equals("increase")){
            var cost = cart.getSumCost()/cart.getAmount();
            cart.setAmount(cart.getAmount() + 1);
            cart.setSumCost(cost * cart.getAmount());
            cartRepository.save(cart);
        }
        return shopController.getAllProducts(model);
    }
}
