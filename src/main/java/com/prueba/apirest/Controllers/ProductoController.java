package com.prueba.apirest.Controllers;

import com.prueba.apirest.Entities.Producto;
import com.prueba.apirest.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    // obtenemos todos los productos
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    // obtenemos un producto por medio de su id
    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id){
        return  productoRepository.findById(id).orElseThrow( () -> new RuntimeException("no se encontro"));
    }

    // guardamos un producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    // actualizamos un producto
    @PutMapping("/{id}")
    // pasamos 2 parametros el id y el body
    public Producto updateProducto(@PathVariable Long id , @RequestBody Producto productoDetalle){
        // creamos un producto al cual le asignamos su metodo respectivo osea traer por id
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("el producto con el id "+id+" no se encontro"));
        // seteamos los parametros y le asignamos los que ingresamos
        producto.setNombre(productoDetalle.getNombre());
        producto.setPrecio(productoDetalle.getPrecio());
        // guardamos el objeto nuevo
        return productoRepository.save(producto);
    }

    // eliminamos un producto
    @DeleteMapping("/{id}")
    // lo hacemos de tipo string
    public String deleteProducto(@PathVariable Long id){
        // creamos una variable a la cual le asignamos el repositorio con su respectivo metodo osea traer por id
        Producto productox = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("no se encontro el producto con el id "+id+" a eliminar"));
        // asignamos el metodo eliminar
        productoRepository.delete(productox);
        // retornamos un mensaje si todo sale bien
        return "El producto con el id "+id+" se elimino correctamente";
    }

}
