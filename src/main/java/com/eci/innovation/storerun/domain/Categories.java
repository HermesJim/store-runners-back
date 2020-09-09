package com.eci.innovation.storerun.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "categories", schema = "shoprun")
public class Categories implements java.io.Serializable {
    @NotNull
    private Long categoryId;
    private String categoryNumber;
    private String description;
    private Long location;
    private String name;
    private List<Items> itemses = new ArrayList<Items>(0);

    public Categories() {
    }

    public Categories(Long categoryId, String categoryNumber,
        String description, List<Items> itemses, Long location, String name) {
        this.categoryId = categoryId;
        this.categoryNumber = categoryNumber;
        this.description = description;
        this.location = location;
        this.name = name;
        this.itemses = itemses;
    }

    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "category_number")
    public String getCategoryNumber() {
        return this.categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "location")
    public Long getLocation() {
        return this.location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    public List<Items> getItemses() {
        return this.itemses;
    }

    public void setItemses(List<Items> itemses) {
        this.itemses = itemses;
    }
}
