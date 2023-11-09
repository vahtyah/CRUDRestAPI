package com.example.mymarketplace.repositories;

import com.example.mymarketplace.models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByIdOfProductAndType(Long idOfProduct, String type);
}
