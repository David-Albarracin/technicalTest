package com.campuslands.technicalTest.product.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campuslands.technicalTest.product.persistence.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalproduct = this.productRepository.findById(id);
        optionalproduct.ifPresent(
                productFound -> {
                    this.productRepository.delete(productFound);
                });
        return optionalproduct;
    }

    public List<Product> findAll() {
        return (List<Product>) this.productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public Product save(Product item) {
        return this.productRepository.save(item);
    }

    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalproduct = this.productRepository.findById(id);
        if (optionalproduct.isPresent()) {
            Product productItem = optionalproduct.orElseThrow();
            // SETS
            productItem.setNombre(product.getNombre());
            productItem.setDescripcion(product.getDescripcion());
            productItem.setPrecio(product.getPrecio());
            productItem.setCategoria(product.getCategoria());
            return Optional.of(this.productRepository.save(productItem));
        }
        return optionalproduct;
    }

}
