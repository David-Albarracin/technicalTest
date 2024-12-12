
package com.campuslands.technicalTest.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campuslands.technicalTest.category.persistence.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}