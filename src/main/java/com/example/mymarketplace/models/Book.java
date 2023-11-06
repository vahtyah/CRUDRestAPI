package com.example.mymarketplace.models;

import jakarta.persistence.*;

//Book – модель, которая описывает сущность книги, с полями:
//        автор, номер продавца, тип продукта (подразумевается
//        электроника, книги, сантехника и т.п.), стоимость, название.
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "seller")
    private String seller;
    @Column(name = "type")
    private String type;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
