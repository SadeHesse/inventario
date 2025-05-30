package com.productos.inventario.model.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Orden {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long ordenId;

    
    /*@OneToMany(mappedBy="producto" )
    private List<Producto> listaProducto;

    @ManyToOne
    @JoinColumn(name="sucursal_id")
    private Sucursal sucursal;*/

    private LocalDate fecha;
    private double total;
    
}
