package com.productos.inventario.model.po;

import java.util.List;

import lombok.Data;

@Data
public class OrdenRequest {

   
    private List<ProductoRequest> productos;
    
    
}
