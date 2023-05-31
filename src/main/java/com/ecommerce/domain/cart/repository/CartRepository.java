package com.ecommerce.domain.cart.repository;

import com.ecommerce.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
