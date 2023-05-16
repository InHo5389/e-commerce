package com.ecommerce.domain.customer.controller;

import com.ecommerce.domain.customer.dto.*;
import com.ecommerce.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;

    // customer 회원가입(인증x)
    @PostMapping("/signup/customer")
    public String signup(@RequestBody CustomerSignUpDto customerSignUpDto) throws Exception {
        customerService.signUp(customerSignUpDto);
        return "회원가입완료";
    }

    // customer 정보 수정(인증o)
    @PutMapping("/customer")
    public String updateCustomerInfo(@RequestBody CustomerUpdateDto customerUpdateDto) throws Exception {
        customerService.update(customerUpdateDto);
        return "회원수정완료";
    }

    // customer 비밀번호 수정(인증o)
    @PutMapping("/customer/password")
    public String updatePassword(@RequestBody CustomerUpdatePasswordDto customerUpdatePasswordDto) throws Exception {
        customerService.updatePassword(customerUpdatePasswordDto.getCheckPassword(),
                customerUpdatePasswordDto.getToBePassword());
        return "회원 비밀번호 수정 완료";
    }

    // customer 회원 탈퇴(인증o)
    @DeleteMapping("/customer")
    public String withdraw(@RequestBody CustomerWithDrawDto customerWithDrawDto) throws Exception {
        customerService.withdraw(customerWithDrawDto.getPassword());
        return "회원이 정상적으로 삭제되었습니다.";
    }

    // customer 정보 조회
    @GetMapping("/customer/{id}")
    public ResponseEntity getInfo(@PathVariable Long id) throws Exception {
        CustomerInfoDto info = customerService.getInfo(id);
        return new ResponseEntity(info, HttpStatus.OK);
    }

    // 내 정보 조회(인증x)
    @GetMapping("/customer")
    public ResponseEntity getMyInfo(HttpServletResponse response) throws Exception {
        CustomerInfoDto myInfo = customerService.getMyInfo();
        return new ResponseEntity(myInfo, HttpStatus.OK);
    }

}
