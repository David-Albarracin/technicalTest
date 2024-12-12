
package com.campuslands.technicalTest.category.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.campuslands.technicalTest.category.domain.CategoryService;
import com.campuslands.technicalTest.category.persistence.Category;

;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> view(@PathVariable Long id){
        Optional<Category> optionalCategory  = categoryService.findById(id);
        if (optionalCategory.isPresent()){
            return ResponseEntity.ok(optionalCategory.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Category category, BindingResult result){
        
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> categoryOptional = this.categoryService.update(id, category);
        if (categoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> delete(@PathVariable Long id){
        //category category = new category();
        //category.setId(id);
        Optional<Category> optionalCategory = this.categoryService.delete(id);
        if (optionalCategory.isPresent()){
            return ResponseEntity.ok(optionalCategory.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}