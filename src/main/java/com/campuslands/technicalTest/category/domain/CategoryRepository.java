
package com.campuslands.technicalTest.category.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campuslands.technicalTest.category.persistence.Categoria;



@Repository
public interface CategoryRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);


}