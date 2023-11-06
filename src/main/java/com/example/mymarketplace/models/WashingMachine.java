package com.example.mymarketplace.models;
//WashingMachine – модель, которая описывает сущность
//        стиральной машины, с такими полями: производитель, объем
//        бака, номер продавца, тип продукта (подразумевается
//        электроника, книги, сантехника и т.п.), стоимость, название.

import jakarta.persistence.*;

@Entity
@Table(name = "washing_machines")
public class WashingMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "tank_volume")
    private Integer tankVolume;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(Integer tankVolume) {
        this.tankVolume = tankVolume;
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
