package com.campuslands.technicalTest.category.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campuslands.technicalTest.category.persistence.Categoria;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public Optional<Categoria> delete(Long id) {
        Optional<Categoria> optionalcategory = this.categoryRepository.findById(id);
        optionalcategory.ifPresent(
                categoryFound -> {
                    this.categoryRepository.delete(categoryFound);
                });
        return optionalcategory;
    }

    public List<Categoria> findAll() {
        return (List<Categoria>) this.categoryRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    public Categoria save(Categoria item) {
        return this.categoryRepository.save(item);
    }

    public Optional<Categoria> update(Long id, Categoria category) {
        Optional<Categoria> optionalcategory = this.categoryRepository.findById(id);
        if (optionalcategory.isPresent()) {
            Categoria categoryItem = optionalcategory.orElseThrow();
            // SETS
            categoryItem.setNombre(category.getNombre());
            return Optional.of(this.categoryRepository.save(categoryItem));
        }
        return optionalcategory;
    }

    public Optional<Categoria> findByNombre(String nombre) {
        return this.categoryRepository.findByNombre(nombre);
    }

}
