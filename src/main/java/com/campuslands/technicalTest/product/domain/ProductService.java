package com.campuslands.technicalTest.product.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campuslands.technicalTest.product.persistence.Producto;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public Optional<Producto> delete(Long id) {
        Optional<Producto> optionalproduct = this.productRepository.findById(id);
        optionalproduct.ifPresent(
                productFound -> {
                    this.productRepository.delete(productFound);
                });
        return optionalproduct;
    }

    public List<Producto> findAll() {
        return (List<Producto>) this.productRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public Producto save(Producto item) {
        return this.productRepository.save(item);
    }

    public Optional<Producto> update(Long id, Producto product) {
        Optional<Producto> optionalproduct = this.productRepository.findById(id);
        if (optionalproduct.isPresent()) {
            Producto productItem = optionalproduct.orElseThrow();
            // SETS
            productItem.setNombre(product.getNombre());
            productItem.setDescripcion(product.getDescripcion());
            productItem.setPrecio(product.getPrecio());
            productItem.setCategoria(product.getCategoria());
            return Optional.of(this.productRepository.save(productItem));
        }
        return optionalproduct;
    }

    public List<Producto> findByCategoriaNombre(String nombre) {
        // Busca los productos cuya categoría tenga el nombre especificado
        return productRepository.findByCategoriaNombre(nombre);
    }

}
