package com.productos.inventario.model.po;

import java.util.List;

import lombok.Data;

@Data
public class OrdenResponse {

    private String sucursal;
    private List<ProductoRequest> productos;

    
}
