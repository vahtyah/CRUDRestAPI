package com.example.mymarketplace.repositories;

import com.example.mymarketplace.models.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashingMachineRepository extends JpaRepository<WashingMachine, Long> {
}
