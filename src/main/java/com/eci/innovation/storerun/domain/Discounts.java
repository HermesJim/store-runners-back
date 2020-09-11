package com.eci.innovation.storerun.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "discounts", schema = "shoprun")
public class Discounts implements java.io.Serializable {
    @NotNull
    private Long discountId;
    @NotNull
    private Items items;
    private String description;
    private String discountNumber;
    private Long discountPercent;
    private String webImage;
    private String name;

    public Discounts() {
    }

    public Discounts(Long discountId, String description,
        String discountNumber, Long discountPercent, Items items, String name) {
        this.discountId = discountId;
        this.items = items;
        this.description = description;
        this.discountNumber = discountNumber;
        this.discountPercent = discountPercent;
        this.name = name;
    }

    @Id
    @Column(name = "discount_id", unique = true, nullable = false)
    public Long getDiscountId() {
        return this.discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    public Items getItems() {
        return this.items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "discount_number")
    public String getDiscountNumber() {
        return this.discountNumber;
    }

    public void setDiscountNumber(String discountNumber) {
        this.discountNumber = discountNumber;
    }

    @Column(name = "discount_percent")
    public Long getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(Long discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "web_image")
	public String getWebImage() {
		return webImage;
	}

	public void setWebImage(String webImage) {
		this.webImage = webImage;
	}
}
