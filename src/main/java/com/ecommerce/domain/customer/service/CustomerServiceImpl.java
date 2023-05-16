package com.ecommerce.domain.customer.service;

import com.ecommerce.domain.SecurityUtil;
import com.ecommerce.domain.customer.Customer;
import com.ecommerce.domain.customer.dto.CustomerInfoDto;
import com.ecommerce.domain.customer.dto.CustomerSignUpDto;
import com.ecommerce.domain.customer.dto.CustomerUpdateDto;
import com.ecommerce.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(CustomerSignUpDto customerSignUpDto) throws Exception {
        Customer customer = customerSignUpDto.toEntity();
        customer.encodePassword(passwordEncoder);
        customer.addCustomerAuthority();

        if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        customerRepository.save(customer);
    }

    @Override
    public void update(CustomerUpdateDto customerUpdateDto) throws Exception {
        Customer customer = customerRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("일치하는 회원이 없습니다."));
        customer.updateName(customerUpdateDto.getName());
        customer.updateNickName(customerUpdateDto.getNickName());
        customer.updateAge(customerUpdateDto.getAge());

    }

    @Override
    public void updatePassword(String checkPassword, String toBePassword) throws Exception {
        Customer customer = customerRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("일치하는 회원이 없습니다."));
        if (!customer.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        customer.updatePassword(passwordEncoder, toBePassword);

        customerRepository.save(customer);
    }

    @Override
    public void withdraw(String checkPassword) throws Exception {
        Customer customer = customerRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("일치하는 회원이 없습니다."));
        if (!customer.matchPassword(passwordEncoder, checkPassword)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        customerRepository.delete(customer);
    }

    @Override
    public CustomerInfoDto getInfo(Long id) throws Exception {
        Customer customer = customerRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("일치하는 회원이 없습니다."));
        if (customer.getRoles().equals("ROLE_ADMIN")) {
            Customer findCustomer = customerRepository.findById(id).orElseThrow(() -> new Exception("회원이 없습니다."));
            return new CustomerInfoDto(findCustomer);
        }
        return new CustomerInfoDto();
    }

    @Override
    public CustomerInfoDto getMyInfo() throws Exception {
        Customer customer = customerRepository.findByUsername(SecurityUtil.getLoginUsername())
                .orElseThrow(() -> new Exception("일치하는 회원이 없습니다."));
        return new CustomerInfoDto(customer);
    }
}
