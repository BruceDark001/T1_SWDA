package com.ecommerce.controller;

import com.ecommerce.dto.ProductoDTO;
import com.ecommerce.entity.Producto;
import com.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return new ResponseEntity<>(productoService.listarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody ProductoDTO dto) {
        Producto nuevoProducto = productoService.guardar(dto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}