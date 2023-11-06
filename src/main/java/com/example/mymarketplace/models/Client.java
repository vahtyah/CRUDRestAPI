package com.example.mymarketplace.models;

import jakarta.persistence.*;

//Client – модель, которая описывает сущность клиента сайта. У
//        модели должны быть поля: имя, электронная почта, логин,
//        пароль.
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name; // имя
    @Column(name = "email")
    private String email; // электронная почта
    @Column(name = "login")
    private String login; // логин
    @Column(name = "password")
    private String password; // пароль

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
