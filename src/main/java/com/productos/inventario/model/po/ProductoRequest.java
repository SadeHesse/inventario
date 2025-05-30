package com.productos.inventario.model.po;

import lombok.Data;

@Data
public class ProductoRequest {
    private String codigo;
    private String descripcion;
    private double precio;
}
