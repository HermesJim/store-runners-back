package com.eci.innovation.storerun.dto;

import com.eci.innovation.storerun.domain.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class CategoriesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CategoriesDTO.class);
    private Long categoryId;
    private String categoryNumber;
    private String description;
    private Long location;
    private String name;
    private List<ItemsDTO> itemses  = new ArrayList<ItemsDTO>(0);

	public List<ItemsDTO> getItemses() {
		return itemses;
	}

	public void setItemses(List<ItemsDTO> itemses) {
		this.itemses = itemses;
	}

	public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());

            return super.toString();
        }
    }
}
