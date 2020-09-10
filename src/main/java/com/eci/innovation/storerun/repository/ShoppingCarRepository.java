package com.eci.innovation.storerun.repository;

import com.eci.innovation.storerun.domain.ShoppingCar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   ShoppingCarRepository.
*
*/
public interface ShoppingCarRepository extends JpaRepository<ShoppingCar, Long> {
}
