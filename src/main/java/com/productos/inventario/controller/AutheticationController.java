package com.productos.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productos.inventario.model.po.AuthUser;
import com.productos.inventario.security.JWTUtil;

@RestController
public class AutheticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtil jwtUtil;
    
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthUser autRequest){
         try{
            authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(autRequest.getUsername(),autRequest.getPassword()));
            return jwtUtil.generateToken(autRequest.getUsername());
         }catch(Exception e){
            throw e;
         }
        
    }
    
}
