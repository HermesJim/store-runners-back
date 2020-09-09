package com.eci.innovation.storerun.controller;

import com.eci.innovation.storerun.domain.*;
import com.eci.innovation.storerun.dto.DiscountsDTO;
import com.eci.innovation.storerun.mapper.DiscountsMapper;
import com.eci.innovation.storerun.service.DiscountsService;

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
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "*")
public class DiscountsRestController {
    private static final Logger log = LoggerFactory.getLogger(DiscountsRestController.class);
    @Autowired
    private DiscountsService discountsService;
    @Autowired
    private DiscountsMapper discountsMapper;

    @GetMapping(value = "/findById/{discountId}")
    public ResponseEntity<?> findById(
        @PathVariable("discountId")
    Long discountId) {
        log.debug("Request to findById() Discounts");

        try {
            Discounts discounts = discountsService.findById(discountId).get();

            return ResponseEntity.ok()
                                 .body(discountsMapper.discountsToDiscountsDTO(
                    discounts));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Discounts");

        try {
            return ResponseEntity.ok()
                                 .body(discountsMapper.listDiscountsToListDiscountsDTO(
                    discountsService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody
    DiscountsDTO discountsDTO) {
        log.debug("Request to save Discounts: {}", discountsDTO);

        try {
            Discounts discounts = discountsMapper.discountsDTOToDiscounts(discountsDTO);
            discounts = discountsService.save(discounts);

            return ResponseEntity.ok()
                                 .body(discountsMapper.discountsToDiscountsDTO(
                    discounts));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody
    DiscountsDTO discountsDTO) {
        log.debug("Request to update Discounts: {}", discountsDTO);

        try {
            Discounts discounts = discountsMapper.discountsDTOToDiscounts(discountsDTO);
            discounts = discountsService.update(discounts);

            return ResponseEntity.ok()
                                 .body(discountsMapper.discountsToDiscountsDTO(
                    discounts));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{discountId}")
    public ResponseEntity<?> delete(@PathVariable("discountId")
    Long discountId) throws Exception {
        log.debug("Request to delete Discounts");

        try {
            Discounts discounts = discountsService.findById(discountId).get();

            discountsService.delete(discounts);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(discountsService.count());
    }
}
