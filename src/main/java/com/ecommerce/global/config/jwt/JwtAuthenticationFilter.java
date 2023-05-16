package com.ecommerce.global.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.domain.customer.Customer;
import com.ecommerce.global.config.auth.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");
        System.out.println("attemptAuthentication함수 실행");

        try {
            ObjectMapper om = new ObjectMapper();
            Customer customer = om.readValue(request.getInputStream(), Customer.class);
            System.out.println(customer);

            UsernamePasswordAuthenticationToken authenticationToken
                    =new UsernamePasswordAuthenticationToken(customer.getUsername(),customer.getPassword());

            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
            System.out.println("로그인 완료됨"+principalDetails.getCustomer().getUsername()+"========");

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=========================");
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("인증 === 완료");
        PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
        String token = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis() +JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getCustomer().getId())
                .withClaim("username", principalDetails.getCustomer().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        response.addHeader(JwtProperties.HEADER_STRING,JwtProperties.TOKEN_PREFIX+token);
    }
}
