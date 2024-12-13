
package com.campuslands.technicalTest.product.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.technicalTest.category.domain.CategoryService;
import com.campuslands.technicalTest.category.persistence.Categoria;
import com.campuslands.technicalTest.product.domain.ProductService;
import com.campuslands.technicalTest.product.persistence.Producto;

import jakarta.validation.Valid;

;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<Producto> listEmployee() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> view(@PathVariable Long id) {
        Optional<Producto> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Producto product) {
        // Si no hay errores de validación, el flujo llega aquí.
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }
    

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> update(@PathVariable Long id,@Valid @RequestBody Producto product) {
        Optional<Producto> productOptional = this.productService.update(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        // product product = new product();
        // product.setId(id);
        Optional<Producto> productOptional = this.productService.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/categoria")
    public ResponseEntity<List<Producto>> getByCategory(@RequestParam("id") Long id) {

         // Verifica si la categoría existe
         Optional<Categoria> categoria = categoryService.findById(id);

         if (categoria.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                  .body(List.of()); // Devolver 404 si no existe la categoría
         }

        // Busca los productos que tienen una categoría con el nombre dado
        List<Producto> productos = productService.findByCategoriaNombre(categoria.get().getNombre());

        // Si la lista de productos está vacía, devuelve una respuesta 404 (Not Found)
        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Devuelve la lista de productos encontrados con el nombre de categoría dado
        return ResponseEntity.ok(productos);
    }

}