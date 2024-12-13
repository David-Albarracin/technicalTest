
package com.campuslands.technicalTest.product.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campuslands.technicalTest.product.persistence.Producto;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaNombre(String nombre);


}