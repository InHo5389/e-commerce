package com.ecommerce.domain.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDto {

    private String name;
    private String nickName;
    private Integer age;
}
