package com.eci.innovation.storerun.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;

/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "shopping_car", schema = "shoprun")
public class ShoppingCar implements java.io.Serializable {
	@NotNull
	private Long cartId;
	@NotNull
	private Categories categories;
	private String description;
	private String itemNumber;
	private Long itemQuantity;
	private String name;
	private Long position;
	private Double posX;
	private Double posY;
	private Double price;
	private String webImage;

	public ShoppingCar() {
	}

	public ShoppingCar(Long cartId, Categories categories, String description, String itemNumber, Long itemQuantity,
			String name, Long position, String positionVector, Double price, String webImage) {
		this.cartId = cartId;
		this.categories = categories;
		this.description = description;
		this.itemNumber = itemNumber;
		this.itemQuantity = itemQuantity;
		this.name = name;
		this.position = position;
		this.price = price;
		this.webImage = webImage;
	}

	@Id
	@Column(name = "cart_id", unique = true, nullable = false)
	public Long getCartId() {
		return this.cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
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

	@Column(name = "position")
	public Long getPosition() {
		return this.position;
	}

	public void setPosition(Long position) {
		this.position = position;
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
		return this.webImage;
	}

	public void setWebImage(String webImage) {
		this.webImage = webImage;
	}

	@Column(name = "pos_x")
	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		this.posX = posX;
	}

	@Column(name = "pos_y")
	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		this.posY = posY;
	}
}
