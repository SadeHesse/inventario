package com.productos.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos.inventario.model.dao.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
    
}
