package com.ecommerce.domain;

import com.ecommerce.global.config.auth.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getLoginUsername(){
        PrincipalDetails user = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
