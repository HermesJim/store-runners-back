package com.eci.innovation.storerun.controller;

import com.eci.innovation.storerun.domain.*;
import com.eci.innovation.storerun.dto.ItemsDTO;
import com.eci.innovation.storerun.mapper.ItemsMapper;
import com.eci.innovation.storerun.service.ItemsService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

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
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
@Api(tags = "Items", description = "Services to get Store Runners Items Data")
public class ItemsRestController {
    private static final Logger log = LoggerFactory.getLogger(ItemsRestController.class);
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private ItemsMapper itemsMapper;

    @GetMapping(value = "/findById/{itemId}")
    public ResponseEntity<?> findById(@PathVariable("itemId")
    Long itemId) {
        log.debug("Request to findById() Items");

        try {
            Items items = itemsService.findById(itemId).get();

            return ResponseEntity.ok().body(itemsMapper.itemsToItemsDTO(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll() {
        log.debug("Request to findAll() Items");

        try {
            return ResponseEntity.ok()
                                 .body(itemsMapper.listItemsToListItemsDTO(
                    itemsService.findAll()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    @ApiIgnore
    public ResponseEntity<?> save(@RequestBody
    ItemsDTO itemsDTO) {
        log.debug("Request to save Items: {}", itemsDTO);

        try {
            Items items = itemsMapper.itemsDTOToItems(itemsDTO);
            items = itemsService.save(items);

            return ResponseEntity.ok().body(itemsMapper.itemsToItemsDTO(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    @ApiIgnore
    public ResponseEntity<?> update(@RequestBody
    ItemsDTO itemsDTO) {
        log.debug("Request to update Items: {}", itemsDTO);

        try {
            Items items = itemsMapper.itemsDTOToItems(itemsDTO);
            items = itemsService.update(items);

            return ResponseEntity.ok().body(itemsMapper.itemsToItemsDTO(items));
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{itemId}")
    @ApiIgnore
    public ResponseEntity<?> delete(@PathVariable("itemId")
    Long itemId) throws Exception {
        log.debug("Request to delete Items");

        try {
            Items items = itemsService.findById(itemId).get();

            itemsService.delete(items);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/count")
    @ApiIgnore
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(itemsService.count());
    }
}
