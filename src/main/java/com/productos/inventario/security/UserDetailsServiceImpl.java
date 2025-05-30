package com.productos.inventario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.productos.inventario.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserDetailsRepository userDetailsRepository;//10

    @Override//9
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails algo= userDetailsRepository
        .findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("No se encontro el usuario ingresado"));

        return algo;
    }
    
}
