package com.example.mymarketplace.repositories;

import com.example.mymarketplace.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findByNameAndType(String name, String type);
}
