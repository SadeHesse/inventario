package com.productos.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productos.inventario.model.dao.Orden;
import com.productos.inventario.model.dao.Producto;
import com.productos.inventario.service.InventarioService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class InventarioController {

    
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService){
        this.inventarioService=inventarioService;
    }

    
    
    @PostMapping("/productos")
    public Producto guardaProductos(@RequestBody Producto producto){
        return inventarioService.guardaProducto(producto);
      
    }

    
}
