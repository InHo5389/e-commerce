package com.ecommerce.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void 패스워드_암호화() throws Exception {
        String password = "정인호jungInHo";
        String encodePassword = passwordEncoder.encode(password);
        System.out.println(encodePassword);
        assertThat(encodePassword).startsWith("{");
        assertThat(encodePassword).contains("{bcrypt}");
        assertThat(encodePassword).isNotEqualTo(password);
    }

    @Test
    public void 패스워드_랜덤_암호화() throws Exception {
        String password = "정인호jungInHo";
        String encodePassword1 = passwordEncoder.encode(password);
        String encodePassword2 = passwordEncoder.encode(password);
        System.out.println(encodePassword1);
        System.out.println(encodePassword2);
        assertThat(encodePassword1).isNotEqualTo(encodePassword2);
    }

    @Test
    public void 암호화된_비밀번호_매치() throws Exception {
        String password = "정인호jungInHo";
        String encodePassword = passwordEncoder.encode(password);
        assertThat(passwordEncoder.matches(password,encodePassword)).isTrue();
    }
}
