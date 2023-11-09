package com.example.mymarketplace.models;

import jakarta.persistence.*;

//Telephone – модель, описывающая сущность телефона.
//        Обязательные поля: производитель, объем аккумулятора,
//        номер продавца, тип продукта (подразумевается электроника,
//        книги, сантехника и т.п.), стоимость, название.
@Entity
@Table(name = "telephones")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "battery_capacity")
    private Integer batteryCapacity;
    @Column(name = "seller")
    private String seller;
    @Column(name = "type")
    private String type;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "name")
    private String name;

    public Telephone(String manufacturer, Integer batteryCapacity, String seller, String type, Integer cost, String name) {
        this.manufacturer = manufacturer;
        this.batteryCapacity = batteryCapacity;
        this.seller = seller;
        this.type = type;
        this.cost = cost;
        this.name = name;
    }

    public Telephone() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
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
