package com.dogproductinventory.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DogProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	private String name, color, size;
	private int price;
	
	
	// Default constructor
	public DogProduct() {
	}

	// Valmistajan lisääminen DogProduct- tauluun
	// Many-to-One tarkoittaa tässä, että tuotteella voi olla yksi valmistaja
	// Manufacturer taulun pääavain on foreign key tässä DogProduct taulussa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer")
	private Manufacturer manufacturer;


	public DogProduct(String name, String color, int price, String size, Manufacturer manufacturer) {
		super();
		this.name = name;
		this.color = color;
		this.price = price;
		this.manufacturer = manufacturer;
		this.size = size;
	}


	// Valmistajan getterit ja setterit
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
}
