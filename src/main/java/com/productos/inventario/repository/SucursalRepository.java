package com.productos.inventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos.inventario.model.dao.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{

    Optional<Sucursal> findByNombre(String nombre);
    
}
