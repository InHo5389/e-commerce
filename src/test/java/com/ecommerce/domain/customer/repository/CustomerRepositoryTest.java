package com.ecommerce.domain.customer.repository;

import com.ecommerce.domain.customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EntityManager em;

    private void clear() {
        em.flush();
        em.clear();
    }

    @AfterEach
    private void after() {
        em.clear();
    }

    @Test
    public void 고객저장_성공() throws Exception {

        Customer customer = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();

        Customer saveCustomer = customerRepository.save(customer);
        Customer findCustomer = customerRepository.findById(saveCustomer.getId()).orElseThrow(() ->
                new RuntimeException("저장된 회원이 없습니다."));

        assertThat(findCustomer).isSameAs(saveCustomer);
        assertThat(findCustomer).isSameAs(customer);
    }

    @Test
    public void 오류_회원가입시_아이디가_없음() throws Exception {
        Customer customer = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        assertThrows(Exception.class, () -> customerRepository.save(customer));
    }

    @Test
    public void 오류_회원가입시_나이가_없음() throws Exception {
        Customer customer = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .build();
        assertThrows(Exception.class, () -> customerRepository.save(customer));
    }

    @Test
    public void 오류_회원가입시_중복된_아이디가_있음() throws Exception {
        Customer customer1 = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        Customer customer2 = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        customerRepository.save(customer1);
        clear();
        assertThrows(Exception.class, () -> customerRepository.save(customer2));
    }

    @Test
    public void 성공_회원수정() throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Customer customer = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        customerRepository.save(customer);

        Customer findCustomer = customerRepository.findById(customer.getId()).orElseThrow(() -> new Exception());
        findCustomer.updateName("inho1");
        findCustomer.updateAge(25);
        findCustomer.updateNickName("이노1");
        findCustomer.updatePassword(passwordEncoder, "12345");
        em.flush();
        Customer findUpdateCustomer = customerRepository.findById(customer.getId()).orElseThrow(() -> new Exception());

        assertThat(findUpdateCustomer).isSameAs(findCustomer);
        assertThat(passwordEncoder.matches("12345", findUpdateCustomer.getPassword())).isTrue();
        assertThat(findUpdateCustomer.getName()).isEqualTo("inho1");
        assertThat(findUpdateCustomer.getName()).isEqualTo(customer.getName());
    }

    @Test
    public void 성공_회원삭제() throws Exception {
        Customer customer = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        customerRepository.save(customer);
        clear();
        customerRepository.delete(customer);
        clear();

        assertThrows(Exception.class,
                () -> customerRepository.findById(customer.getId())
                        .orElseThrow(() -> new Exception()));
    }

    @Test
    public void existsByEmail() throws Exception {
        String username = "인호";
        Customer customer = Customer.builder()
                .username(username)
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        customerRepository.save(customer);
        clear();

        assertThat(customerRepository.existsByUsername(username)).isTrue();
        assertThat(customerRepository.existsByUsername(username + "123")).isFalse();
    }

    @Test
    public void findByEmail_정상작동() throws Exception {
        String username = "인호";
        Customer customer = Customer.builder()
                .username(username)
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        customerRepository.save(customer);
        clear();

        assertThat(customerRepository.findByUsername(username).get().getUsername()).isEqualTo(username);
        assertThat(customerRepository.findByUsername(username).get().getName()).isEqualTo(customer.getName());
        assertThat(customerRepository.findByUsername(username).get().getId()).isEqualTo(customer.getId());
        assertThrows(Exception.class,
                () -> customerRepository.findByUsername(username + "123")
                        .orElseThrow(() -> new Exception()));
    }

    @Test
    public void 회원가입시_생성시간_등록() throws Exception {
        Customer customer = Customer.builder()
                .username("인호")
                .password("1234")
                .name("inho")
                .phone("010-1111-1111")
                .nickName("이노")
                .address("서울시 금천구 독산동")
                .age(26)
                .build();
        customerRepository.save(customer);
        clear();

        Customer findCustomer = customerRepository.findById(customer.getId()).orElseThrow(() -> new Exception());

        assertThat(findCustomer.getCreatedDate()).isNotNull();
        assertThat(findCustomer.getUpdatedDate()).isNotNull();
    }


}