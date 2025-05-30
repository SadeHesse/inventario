package com.productos.inventario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.productos.inventario.model.dao.Orden;
import com.productos.inventario.model.dao.Producto;
import com.productos.inventario.model.dao.Sucursal;
import com.productos.inventario.model.po.OrdenRequest;
import com.productos.inventario.model.po.OrdenResponse;
import com.productos.inventario.model.po.IdsOrdenResponse;
import com.productos.inventario.model.po.ProductoRequest;
import com.productos.inventario.model.po.SucursalRequest;
import com.productos.inventario.repository.OrdenesRepository;
import com.productos.inventario.repository.ProductosRepository;
import com.productos.inventario.repository.SucursalRepository;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final OrdenesRepository ordenesRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductosRepository productosRepository;

    public InventarioServiceImpl(ProductosRepository productosRepository, OrdenesRepository ordenesRepository,
            SucursalRepository sucursalRepository) {
        this.productosRepository = productosRepository;
        this.ordenesRepository = ordenesRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public IdsOrdenResponse guardaOrdenCompra(SucursalRequest request) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(request.getName());

        List<Orden> ordenes = new ArrayList<Orden>();
        List<Producto> productos = new ArrayList<Producto>();
        Orden ordenActual = null;

        Optional<Sucursal> sucursalExiste = sucursalRepository.findByNombre(request.getName());
        if (!sucursalExiste.isPresent()) {
            sucursal = sucursalRepository.saveAndFlush(sucursal);
        } else {
            sucursal = sucursalExiste.get();
        }

        for (OrdenRequest ordenRequest : request.getOrdenes()) {
            Orden orden = new Orden();
            orden.setFecha(LocalDate.now());
            orden.setSucursal(sucursal);
            if (!ordenRequest.getProductos().isEmpty()) {
                orden.setTotal(ordenRequest.getProductos().stream().mapToDouble(m -> m.getPrecio()).sum());
            }

            ordenActual = ordenesRepository.save(orden);
            for (ProductoRequest productoRequest : ordenRequest.getProductos()) {

                Producto producto = new Producto();
                producto.setCodigo(productoRequest.getCodigo());
                producto.setDescripcion(productoRequest.getDescripcion());
                producto.setPrecio(productoRequest.getPrecio());
                producto.setOrden(orden);
                productosRepository.saveAndFlush(producto);
                productos.add(producto);
            }

            orden.setListaProducto(productos);

            ordenes.add(orden);
        }
        sucursal.setListaOrdenes(ordenes);
        IdsOrdenResponse ordenResponse = new IdsOrdenResponse();
        List<Long> idsOrdenes = ordenActual.getListaProducto().stream().map(p -> p.getOrden().getId()).distinct().toList();
        ordenResponse.setOrdenesCompraId(idsOrdenes);

        return ordenResponse;

    }

    public OrdenResponse getOrdenCompra(Long id) {
        OrdenResponse ordenResponse = new OrdenResponse();
        Optional<Orden> ordenOptional = ordenesRepository.findById(id);
        if (ordenOptional.isPresent()) {
            Orden orden = ordenOptional.get();
            ordenResponse.setSucursal(orden.getSucursal().getNombre());
            List<ProductoRequest> listProduct = new ArrayList<>();

            orden.getListaProducto().stream().forEach(p -> {
                ProductoRequest productoRequest = new ProductoRequest();
                productoRequest.setCodigo(p.getCodigo());
                productoRequest.setDescripcion(p.getDescripcion());
                productoRequest.setPrecio(p.getPrecio());
                listProduct.add(productoRequest);
            });
            ordenResponse.setProductos(listProduct);

        }
        return ordenResponse;

    }

    public String updateProducto(String codigo, double precio){
        Optional<List<Producto>> productoOptional=productosRepository.findByCodigo(codigo);

        if(productoOptional.isPresent()){
            List<Producto> productos=productoOptional.get();
            productos.stream().forEach(p->{
                p.setPrecio(precio);
                productosRepository.save(p);
            });

        }
        return "ok";
    }

}
