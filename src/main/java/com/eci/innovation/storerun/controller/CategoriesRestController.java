package com.eci.innovation.storerun.controller;

import com.eci.innovation.storerun.domain.*;
import com.eci.innovation.storerun.dto.CategoriesDTO;
import com.eci.innovation.storerun.mapper.CategoriesMapper;
import com.eci.innovation.storerun.mapper.DiscountsMapper;
import com.eci.innovation.storerun.service.CategoriesService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
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
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
@Api(tags = "Categories", description = "Services to get Store Runners Categories Data")
public class CategoriesRestController {
	private static final Logger log = LoggerFactory.getLogger(CategoriesRestController.class);
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private CategoriesMapper categoriesMapper;
	@Autowired
	private DiscountsMapper discountsMapper;

	@GetMapping(value = "/findById/{categoryId}")
	public ResponseEntity<?> findById(@PathVariable("categoryId") Long categoryId) {
		log.debug("Request to findById() Categories");

		try {
			Categories categories = categoriesService.findById(categoryId).get();

			return ResponseEntity.ok().body(categoriesMapper.categoriesToCategoriesDTO(categories));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/findDiscountsById/{categoryId}")
	public ResponseEntity<?> findDiscountsById(@PathVariable("categoryId") Long categoryId) {
		log.debug("Request to findDiscountsById() Categories");

		try {
			Categories categories = categoriesService.findById(categoryId).get();

			List<Items> items = categories.getItemses();
			List<Discounts> discounts = new ArrayList<>();

			for (Items item : items) {
				if (item.getDiscountses() != null && !item.getDiscountses().isEmpty()) {
					discounts.add(item.getDiscountses().get(0));
				}

			}

			if (discounts != null && !discounts.isEmpty()) {
				return ResponseEntity.ok().body(discountsMapper.listDiscountsToListDiscountsDTO(discounts));
			} else {
				return (ResponseEntity<?>) ResponseEntity.noContent();
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		log.debug("Request to findAll() Categories");

		try {
			return ResponseEntity.ok()
					.body(categoriesMapper.listCategoriesToListCategoriesDTO(categoriesService.findAll()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/save")
	@ApiIgnore
	public ResponseEntity<?> save(@RequestBody CategoriesDTO categoriesDTO) {
		log.debug("Request to save Categories: {}", categoriesDTO);

		try {
			Categories categories = categoriesMapper.categoriesDTOToCategories(categoriesDTO);
			categories = categoriesService.save(categories);

			return ResponseEntity.ok().body(categoriesMapper.categoriesToCategoriesDTO(categories));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	@ApiIgnore
	public ResponseEntity<?> update(@RequestBody CategoriesDTO categoriesDTO) {
		log.debug("Request to update Categories: {}", categoriesDTO);

		try {
			Categories categories = categoriesMapper.categoriesDTOToCategories(categoriesDTO);
			categories = categoriesService.update(categories);

			return ResponseEntity.ok().body(categoriesMapper.categoriesToCategoriesDTO(categories));
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/delete/{categoryId}")
	@ApiIgnore
	public ResponseEntity<?> delete(@PathVariable("categoryId") Long categoryId) throws Exception {
		log.debug("Request to delete Categories");

		try {
			Categories categories = categoriesService.findById(categoryId).get();

			categoriesService.delete(categories);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping(value = "/count")
	@ApiIgnore
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(categoriesService.count());
	}
}
