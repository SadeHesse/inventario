package com.productos.inventario.model.po;

import java.util.List;


import lombok.Data;

@Data
public class SucursalRequest {

    private String name;
    private List<OrdenRequest> ordenes;
    
}
