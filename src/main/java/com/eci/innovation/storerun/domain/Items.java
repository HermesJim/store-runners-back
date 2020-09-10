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
@Table(name = "items", schema = "shoprun")
public class Items implements java.io.Serializable {
    @NotNull
    private Long itemId;
    @NotNull
    private Categories categories;
    private String description;
    private String itemNumber;
    private Long itemQuantity;
    private String name;
    private Double price;
    private String webImage;   
	private List<Discounts> discountses = new ArrayList<Discounts>(0);

    public Items() {
    }

    public Items(Long itemId, Categories categories, String description,
        List<Discounts> discountses, String itemNumber, Long itemQuantity,
        String name, Double price) {
        this.itemId = itemId;
        this.categories = categories;
        this.description = description;
        this.itemNumber = itemNumber;
        this.itemQuantity = itemQuantity;
        this.name = name;
        this.price = price;
        this.discountses = discountses;
    }

    @Id
    @Column(name = "item_id", unique = true, nullable = false)
    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public Categories getCategories() {
        return this.categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "item_number")
    public String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Column(name = "item_quantity")
    public Long getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Column(name = "web_image")
    public String getWebImage() {
		return webImage;
	}

	public void setWebImage(String webImage) {
		this.webImage = webImage;
	}


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "items")
    public List<Discounts> getDiscountses() {
        return this.discountses;
    }

    public void setDiscountses(List<Discounts> discountses) {
        this.discountses = discountses;
    }
}
