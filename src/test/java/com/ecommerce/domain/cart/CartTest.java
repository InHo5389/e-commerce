package com.ecommerce.domain.cart;

import com.ecommerce.domain.cart.repository.CartRepository;
import com.ecommerce.domain.customer.Customer;
import com.ecommerce.domain.customer.dto.CustomerSignUpDto;
import com.ecommerce.domain.customer.repository.CustomerRepository;
import com.ecommerce.domain.customer.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Customer createCustomer() throws Exception {
        CustomerSignUpDto customerSignUpDto = new CustomerSignUpDto();
        customerSignUpDto.setUsername("inho1234");
        customerSignUpDto.setPassword("1234");
        customerSignUpDto.setName("junginho");
        customerSignUpDto.setPhone("01012345678");
        customerSignUpDto.setNickName("raon");
        customerSignUpDto.setAddress("서울시관악구신림동");
        customerSignUpDto.setAge(16);
        customerService.signUpCustomer(customerSignUpDto);
        Customer customer = customerRepository.findByUsername(customerSignUpDto.getUsername()).get();
        return customer;
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest() throws Exception {
        Customer customer = createCustomer();

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(savedCart.getCustomer().getId(),customer.getId());
    }

}