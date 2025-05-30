package com.productos.inventario.service;


import com.productos.inventario.model.po.IdsOrdenResponse;
import com.productos.inventario.model.po.OrdenResponse;
import com.productos.inventario.model.po.SucursalRequest;

public interface InventarioService {

    public IdsOrdenResponse guardaOrdenCompra(SucursalRequest request);


     public OrdenResponse getOrdenCompra(Long id);

     public String updateProducto(String codigo, double precio);
    
}
