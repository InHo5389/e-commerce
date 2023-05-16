package com.ecommerce.domain.customer.dto;

import lombok.Getter;

@Getter
public class CustomerUpdatePasswordDto {

    private String checkPassword;
    private String toBePassword;
}
