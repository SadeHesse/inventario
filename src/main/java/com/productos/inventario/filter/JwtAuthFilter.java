package com.productos.inventario.filter;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.productos.inventario.security.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwt;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String autHeader=request.getHeader("Authorization");

                if(autHeader!=null && autHeader.startsWith("Bearer")){
                    String token=autHeader.substring(7);
                    String username=jwt.extractUsername(token);

                    if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                        UserDetails user=userDetailsService.loadUserByUsername(username);
                        if(jwt.validateToken(username, user, token)){
                         UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
                         authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                         SecurityContextHolder.getContext().setAuthentication(authToken);
                        }

                    }
                    


                }
                filterChain.doFilter(request, response);
    }
    
}

