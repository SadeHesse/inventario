package com.productos.inventario.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productos.inventario.model.dao.Orden;
import com.productos.inventario.model.dao.Producto;
import com.productos.inventario.model.dao.Sucursal;
import com.productos.inventario.repository.OrdenesRepository;
import com.productos.inventario.repository.ProductosRepository;
import com.productos.inventario.repository.SucursalRepository;

@Service
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    ProductosRepository productosRepository;
    
    @Autowired
    OrdenesRepository ordenesRepository;

    @Autowired
    SucursalRepository sucursalRepository;

    /*public Orden guardaOrden(Producto producto){
        Sucursal sucursal=new Sucursal();
        sucursal.setNombre("Coacalco");

        Orden orden=new Orden();
        orden.setFecha(new Date());
        orden.setSucursal(sucursal);
        // orden.setListaProductos(List.of(producto));
        orden.setTotal(67);
            return ordenesRepository.save(orden);
    }*/

    public Producto guardaProducto(Producto producto){
        /*Producto producto=new Producto();
        producto.setCodigo("123123");
        producto.setDescripcion("martillo");
        producto.setPrecio(50.0);*/
        return productosRepository.save(producto);
    }
    
}
