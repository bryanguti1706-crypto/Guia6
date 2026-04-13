package soda.api.service;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import soda.api.entity.Product;
import soda.api.repository.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public List<Product> get() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(int id) {
        return productRepository.findById(id);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public Product update(int id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            return productRepository.save(updatedProduct);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
    }
}