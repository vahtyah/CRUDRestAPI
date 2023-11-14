package com.example.mymarketplace;

import com.example.mymarketplace.models.Role;
import com.example.mymarketplace.models.User;
import com.example.mymarketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MyMarketPlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMarketPlaceApplication.class, args);
    }
}
