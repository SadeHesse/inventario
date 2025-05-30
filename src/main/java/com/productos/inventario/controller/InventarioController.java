package com.productos.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productos.inventario.model.dao.Orden;
import com.productos.inventario.model.dao.Producto;
import com.productos.inventario.model.dao.Sucursal;
import com.productos.inventario.model.po.IdsOrdenResponse;
import com.productos.inventario.model.po.OrdenResponse;
import com.productos.inventario.model.po.ProductoUpdateRequest;
import com.productos.inventario.model.po.SucursalRequest;
import com.productos.inventario.service.InventarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class InventarioController {

    
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService){
        this.inventarioService=inventarioService;
    }
    
    @PostMapping("/orden")
    public IdsOrdenResponse guardaOrdenDeCompra(@RequestBody SucursalRequest sucursalRequest){
        return inventarioService.guardaOrdenCompra(sucursalRequest);
      
    }

    @GetMapping("/orden")
    public OrdenResponse getOrdenDeCompra(@RequestParam (name="id") Long id){
       return inventarioService.getOrdenCompra(id);
      
    }

    @PatchMapping("/orden")
    public String updatePrecio(@RequestBody ProductoUpdateRequest request ){
        return inventarioService.updateProducto(request.getCodigo(), request.getPrecio());

    }

    
}
