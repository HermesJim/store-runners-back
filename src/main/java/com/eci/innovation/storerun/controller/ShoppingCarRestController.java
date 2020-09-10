package com.eci.innovation.storerun.controller;

import com.eci.innovation.storerun.domain.*;
import com.eci.innovation.storerun.dto.ShoppingCarDTO;
import com.eci.innovation.storerun.mapper.ShoppingCarMapper;
import com.eci.innovation.storerun.service.ShoppingCarService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/shoppingCar")
@CrossOrigin(origins = "*")
@Api(tags = "Shopping Cart", description = "Services to get Store Runners Shopping Cart Data")
public class ShoppingCarRestController {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCarRestController.class);
    @Autowired
    private ShoppingCarService shoppingCarService;
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @GetMapping(value = "/findById/{cartId}")
    public ResponseEntity<?> findById(@PathVariable("cartId")
    Long cartId) {
        log.debug("Request to findById() ShoppingCar");

        try {
            ShoppingCar shoppingCar = shoppingCarService.findById(cartId).get();

            return ResponseEntity.ok()
                                 .body(shoppingCarMapper.shoppingCarToShoppingCarDTO(
                    shoppingCar));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() ShoppingCar");

        try {
            return ResponseEntity.ok()
                                 .body(shoppingCarMapper.listShoppingCarToListShoppingCarDTO(
                    shoppingCarService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    ShoppingCarDTO shoppingCarDTO) {
        log.debug("Request to save ShoppingCar: {}", shoppingCarDTO);

        try {
            ShoppingCar shoppingCar = shoppingCarMapper.shoppingCarDTOToShoppingCar(shoppingCarDTO);
            shoppingCar = shoppingCarService.save(shoppingCar);

            return ResponseEntity.ok()
                                 .body(shoppingCarMapper.shoppingCarToShoppingCarDTO(
                    shoppingCar));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping(value = "/optimize")
    public ResponseEntity<?> optimize(@RequestBody
    List<ShoppingCarDTO> shoppingCarDTOs) {
        log.debug("Request to optimize ShoppingCar: {}", shoppingCarDTOs);

        try {
            List<ShoppingCar> shoppingCars = shoppingCarMapper.listShoppingCarDTOToListShoppingCar(shoppingCarDTOs);
            Collections.sort(shoppingCars, (a, b) -> a.getPosition().compareTo(b.getPosition()));            

            return ResponseEntity.ok()
                                 .body(shoppingCarMapper.listShoppingCarToListShoppingCarDTO(
                                		 shoppingCars));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    ShoppingCarDTO shoppingCarDTO) {
        log.debug("Request to update ShoppingCar: {}", shoppingCarDTO);

        try {
            ShoppingCar shoppingCar = shoppingCarMapper.shoppingCarDTOToShoppingCar(shoppingCarDTO);
            shoppingCar = shoppingCarService.update(shoppingCar);

            return ResponseEntity.ok()
                                 .body(shoppingCarMapper.shoppingCarToShoppingCarDTO(
                    shoppingCar));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{cartId}")
    public ResponseEntity<?> delete(@PathVariable("cartId")
    Long cartId) throws Exception {
        log.debug("Request to delete ShoppingCar");

        try {
            ShoppingCar shoppingCar = shoppingCarService.findById(cartId).get();

            shoppingCarService.delete(shoppingCar);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    @ApiIgnore
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(shoppingCarService.count());
    }
}
