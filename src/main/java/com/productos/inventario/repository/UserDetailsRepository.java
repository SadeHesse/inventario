package com.productos.inventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos.inventario.model.dao.User;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
