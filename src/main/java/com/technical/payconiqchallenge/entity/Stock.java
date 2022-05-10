package com.technical.payconiqchallenge.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "current_price")
	@NotNull
	private int currentPrice;

	@Basic(optional = false)
	@Column(name = "last_update", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Stock() {

	}

	public Stock(int id, String name, int currentPrice, Date lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdate = lastUpdate;
	}

}
