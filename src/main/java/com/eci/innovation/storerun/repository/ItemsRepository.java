package com.eci.innovation.storerun.repository;

import com.eci.innovation.storerun.domain.Items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;


/**
* Interface for   ItemsRepository.
*
*/
public interface ItemsRepository extends JpaRepository<Items, Long> {
}
