package com.ecommerce.domain.customer.dto;

import com.ecommerce.domain.customer.Customer;
import lombok.Data;

@Data
public class CustomerSignUpDto {

    private String username;
    private String password;
    private String name;
    private String phone;
    private String nickName;
    private String address;
    private Integer age;

    public Customer toEntity(){
        return Customer.builder()
                .username(username)
                .password(password)
                .name(name)
                .phone(phone)
                .nickName(nickName)
                .address(address)
                .age(age)
                .build();
    }
}
