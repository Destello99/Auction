package com.project.customer.repositories;

import com.project.customer.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByCustomerId(Integer customerId);
}
