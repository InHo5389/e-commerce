package com.ecommerce.domain.customer.service;

import com.ecommerce.domain.customer.dto.CustomerInfoDto;
import com.ecommerce.domain.customer.dto.CustomerSignUpDto;
import com.ecommerce.domain.customer.dto.CustomerUpdateDto;

public interface CustomerService {

    void signUp(CustomerSignUpDto customerSignUpDto) throws Exception;

    void update(CustomerUpdateDto customerUpdateDto) throws Exception;

    void updatePassword(String checkPassword, String toBePassword) throws Exception;

    void withdraw(String checkPassword) throws Exception;

    CustomerInfoDto getInfo(Long id) throws Exception;

    CustomerInfoDto getMyInfo() throws Exception;
}
