package com.campuslands.technicalTest.category.domain;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campuslands.technicalTest.category.persistence.Category;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

@Transactional
    public Optional<Category> delete(Long id) {
        Optional<Category> optionalcategory = this.categoryRepository.findById(id);
        optionalcategory.ifPresent(
            categoryFound -> {
                this.categoryRepository.delete(categoryFound);
            }
        );
        return optionalcategory;
    }
 
    public List<Category> findAll() {
        return (List<Category>) this.categoryRepository.findAll();
    }


    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    public Category save(Category item) {
        return this.categoryRepository.save(item);
    }

    public Optional<Category> update(Long id, Category category) {
        Optional<Category> optionalcategory = this.categoryRepository.findById(id);
        if (optionalcategory.isPresent()) {
            Category categoryItem = optionalcategory.orElseThrow();
            //SETS
            
            return Optional.of(this.categoryRepository.save(categoryItem));
        }
        return optionalcategory;
    }

}
