package soda.api.controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import soda.api.entity.Product;
import soda.api.service.ProductService;

@CrossOrigin(origins = "*") // Permitir acceso desde cualquier origen
@Tag(name = "Products", description = "API para gestionar productos") // Grupo en Swagger
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de productos")
    public List<Product> get() {
        return productService.get();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por ID", description = "Busca un producto en la base de datos según su ID")
    public Product getById(@PathVariable int id) {
        return productService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Agrega un nuevo producto a la base de datos")
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @Operation(summary = "Modificar un producto", description = "Modifica un producto existente en la base de datos")
    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de la base de datos")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}