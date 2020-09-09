package com.eci.innovation.storerun.repository;

import com.eci.innovation.storerun.domain.Categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   CategoriesRepository.
*
*/
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
