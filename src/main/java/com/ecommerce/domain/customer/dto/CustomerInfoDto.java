package com.ecommerce.domain.customer.dto;

import com.ecommerce.domain.customer.Customer;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CustomerInfoDto {

    private Long id;
    private String username;
    private String name;
    private String phone;
    private String nickName;
    private String address;
    private Integer age;
    private String roles;

    @Builder
    public CustomerInfoDto(Customer customer) {
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.nickName = customer.getNickName();
        this.address = customer.getAddress();
        this.age = customer.getAge();
        this.roles = customer.getRoles();
    }


}
