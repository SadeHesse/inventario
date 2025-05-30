package com.productos.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productos.inventario.model.dao.Producto;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductosRepository  extends JpaRepository<Producto, Long>{

    Optional<List<Producto>> findByCodigo(String codigo);
    
}
