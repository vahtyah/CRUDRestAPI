package com.example.mymarketplace.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_of_product")
    private Long idOfProduct;
    @Column(name = "name")
    private String name;
    @Column(name = "sum_cost")
    private Integer sumCost;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "type")
    private String type;

    public Cart(Long idOfProduct,String name, Integer sumCost, Integer amount, String type) {
        this.idOfProduct = idOfProduct;
        this.name = name;
        this.sumCost = sumCost;
        this.amount = amount;
        this.type = type;
    }

    public Cart() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Integer getSumCost() {
        return sumCost;
    }

    public Integer getAmount() {
        return amount;
    }

    public Long getIdOfProduct() {
        return idOfProduct;
    }

    public String getType() {
        return type;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setSumCost(Integer sumCost) {
        this.sumCost = sumCost;
    }

}
