package com.ecommerce.domain.cart;

import com.ecommerce.domain.customer.Customer;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
