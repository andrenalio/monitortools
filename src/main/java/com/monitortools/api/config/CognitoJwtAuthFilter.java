//package com.monitortools.api.config;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.monitortools.api.util.JwtUtils;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class CognitoJwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtUtils jwtUtils;
//
//    public CognitoJwtAuthFilter(JwtUtils jwtUtils) {
//        this.jwtUtils = jwtUtils;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain)
//            throws ServletException, IOException {
//
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            String token = header.substring(7);
//            if (jwtUtils.validateToken(token)) {
//                String username = jwtUtils.extractUsername(token);
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
