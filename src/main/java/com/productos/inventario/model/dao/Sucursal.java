package com.productos.inventario.model.dao;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Sucursal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    //@OneToMany(mappedBy="sucursal" )
     //private List<Orden> listaOrdenes;
    private String nombre;
    
}
