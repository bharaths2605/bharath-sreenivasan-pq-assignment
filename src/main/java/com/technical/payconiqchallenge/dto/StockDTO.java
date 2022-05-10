package com.technical.payconiqchallenge.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

/**
 * @author Bharath
 *
 */
public class StockDTO {

	private int id;

	@NotNull
	private String name;

	@NotNull
	private int currentPrice;

	private Timestamp lastUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public StockDTO(int id, String name, int currentPrice, Timestamp lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}

	public StockDTO() {

	}

	public StockDTO(@NotNull String name, @NotNull int currentPrice) {
		super();
		this.name = name;
		this.currentPrice = currentPrice;
	}

}
