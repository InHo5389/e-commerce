package com.ecommerce.domain.customer;

import com.ecommerce.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30,unique = true)
    private String username;

    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 30)
    private String nickName;

    @Column(nullable = false, length = 30)
    private String address;

    @Column(nullable = false, length = 30)
    private Integer age;

    private String roles;

    public List<String> getRoleList(){
        if (this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void updatePassword(PasswordEncoder passwordEncoder,String password){
        this.password = passwordEncoder.encode(password);
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateNickName(String nickName){
        this.nickName = nickName;
    }

    public void updateAge(int age){
        this.age = age;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    //비밀번호 변경, 회원 탈퇴 시, 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단하는 메서드입니다.
    public boolean matchPassword(PasswordEncoder passwordEncoder,String checkPassword){
        return passwordEncoder.matches(checkPassword,this.getPassword());
    }

    public void addCustomerAuthority(){
        this.roles = "ROLE_CUSTOMER";
    }

}
