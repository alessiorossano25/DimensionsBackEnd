package com.progetto.Dimensions.config;

import com.progetto.Dimensions.service.JwtService;
import com.progetto.Dimensions.service.UserService;
import jakarta.servlet.ServletException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserService userService;
    private final JwtService jwtService;


    public JwtRequestFilter(HandlerExceptionResolver handlerExceptionResolver, UserService userService, JwtService jwtService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
        final String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String jwt = authenticationHeader.substring(7);
            String mail = jwtService.extractMail(jwt);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (mail != null && authentication == null) {
                UserDetails userDetails = userService.loadUserByMail(mail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}

