package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.models.Cart;
import com.example.mymarketplace.models.Telephone;
import com.example.mymarketplace.models.WashingMachine;
import com.example.mymarketplace.repositories.BookRepository;
import com.example.mymarketplace.repositories.CartRepository;
import com.example.mymarketplace.repositories.TelephoneRepository;
import com.example.mymarketplace.repositories.WashingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping
    public String getAllCarts(Model model){
        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        model.addAttribute("carts", carts);
        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam(value = "type", defaultValue = "book") String type, Model model){
        if(cartRepository.findByIdOfProductAndType(id, type) == null){
            if(type.equals("book")){
                Book book = bookRepository.findById(id).get();
                Cart cart = new Cart(id, book.getName(), book.getCost(), 1, type);
                cartRepository.save(cart);
            }
            else if(type.equals("telephone")){
                Telephone telephone = telephoneRepository.findById(id).get();
                Cart cart = new Cart(id, telephone.getName(), telephone.getCost(), 1, type);
                cartRepository.save(cart);
            }
            else if(type.equals("washingMachine")){
                WashingMachine washingMachine = washingMachineRepository.findById(id).get();
                Cart cart = new Cart(id, washingMachine.getName(), washingMachine.getCost(), 1, type);
                cartRepository.save(cart);
            }
        }
        else{
            Cart cart = cartRepository.findByIdOfProductAndType(id, type);
            var cost = cart.getSumCost()/cart.getAmount();
            cart.setAmount(cart.getAmount() + 1);
            cart.setSumCost(cost * cart.getAmount());
            cartRepository.save(cart);
        }
        model.addAttribute("carts", cartRepository.findAll());
        return "cart";
    }

}
