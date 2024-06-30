package com.whilabel_renewal.whilabel_backend.util;

import com.whilabel_renewal.whilabel_backend.jwt.JwtTokenManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

public class UserIdExtractUtil {

    static public Long extractUserIdFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        JwtTokenManager jwtTokenManager = new JwtTokenManager();
        String token = jwtTokenManager.getTokenFromHeader(header);
        Long userId = jwtTokenManager.extractUserId(token);
        return userId;
    }
}
