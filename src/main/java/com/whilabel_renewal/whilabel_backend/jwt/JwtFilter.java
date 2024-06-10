package com.whilabel_renewal.whilabel_backend.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        List<String> list = new ArrayList<String>(List.of("/api/user/login"));


        if (list.contains(request.getRequestURI())) {
            filterChain.doFilter(request,response);
            return;
        }

        String header = request.getHeader("Authorization");

        try {
            if (*)
        }

    }
}
