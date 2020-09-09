package com.eci.innovation.storerun.repository;

import com.eci.innovation.storerun.domain.Discounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   DiscountsRepository.
*
*/
public interface DiscountsRepository extends JpaRepository<Discounts, Long> {
}
