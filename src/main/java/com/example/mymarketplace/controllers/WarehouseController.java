package com.example.mymarketplace.controllers;

import com.example.mymarketplace.models.Book;
import com.example.mymarketplace.models.Telephone;
import com.example.mymarketplace.models.Warehouse;
import com.example.mymarketplace.models.WashingMachine;
import com.example.mymarketplace.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ShopController shopController;


    @GetMapping
    public String getAllWarehouses(Model model){
        List<Warehouse> warehouses = (List<Warehouse>) warehouseRepository.findAll();
        warehouses.sort(Comparator.comparing(Warehouse::getId));
        model.addAttribute("warehouses", warehouses);
        return "warehouse";
    }

    @PostMapping("/add")
    public String addWarehouse(@RequestParam String name, @RequestParam Integer amount, @RequestParam String type, Model model){
        Warehouse warehouse = new Warehouse(name, amount, type);
        warehouseRepository.save(warehouse);
        return getAllWarehouses(model);
    }


    @GetMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable Long id, Model model){
        try{
            warehouseRepository.deleteById(id);
            return getAllWarehouses(model);
        }
        catch (Exception e){
            return "Warehouse not found";
        }
    }

//    <a th:href="@{/warehouses/reload}" class="btn btn-success">Reload</a>

    @GetMapping("/reload")
    public String reload(Model model){
        List<Book> books = shopController.getAllBooks();
        List<Telephone> telephones = shopController.getAllTelephones();
        List<WashingMachine> washingMachines = shopController.getAllWashingMachines();

        books.forEach(book -> {
            Warehouse warehouse = warehouseRepository.findByNameAndType(book.getName(), "Book");
            if(warehouse == null){
                warehouse = new Warehouse(book.getName(), 1, "Book");
                warehouseRepository.save(warehouse);
            }
        });

        telephones.forEach(telephone -> {
            Warehouse warehouse = warehouseRepository.findByNameAndType(telephone.getName(), "Telephone");
            if(warehouse == null){
                warehouse = new Warehouse(telephone.getName(), 1, "Telephone");
                warehouseRepository.save(warehouse);
            }
        });

        washingMachines.forEach(washingMachine -> {
            Warehouse warehouse = warehouseRepository.findByNameAndType(washingMachine.getName(), "WashingMachine");
            if(warehouse == null){
                warehouse = new Warehouse(washingMachine.getName(), 1, "WashingMachine");
                warehouseRepository.save(warehouse);
            }
        });

        return getAllWarehouses(model);
    }

//            <td>
//            <form action="/carts/updateAmount" method="post">
//                <input type="hidden" name="id" th:value="${warehouse.id}">
//                <button type="submit" name="action" value="decrease">-</button>
//                <span th:text="${warehouse.amount}"></span>
//                <button type="submit" name="action" value="increase">+</button>
//            </form>
//        </td>

    @PostMapping("/updateAmount")
    public String updateAmount(@RequestParam Long id, @RequestParam String action, Model model){
        Warehouse warehouse = warehouseRepository.findById(id).get();
        if(action.equals("decrease")){
            if(warehouse.getAmount() > 0){
                warehouse.setAmount(warehouse.getAmount() - 1);
                warehouseRepository.save(warehouse);
            }
        }
        else if(action.equals("increase")){
            warehouse.setAmount(warehouse.getAmount() + 1);
            warehouseRepository.save(warehouse);
        }
        return getAllWarehouses(model);
    }


}
