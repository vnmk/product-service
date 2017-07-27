package com.myretail.service.product;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "Product")
public class Product {
	private long id;
	private String name;

	@JsonProperty("current_price")
	private CurrentPrice price;

	public Product() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPrice getPrice() {
		return price;
	}

	public void setPrice(CurrentPrice price) {
		this.price = price;
	}

	class CurrentPrice {

		private double value;

		@JsonProperty("currency_code")
		private String currency;

		public CurrentPrice() {
			super();
		}

		public CurrentPrice(double value, String currency) {
			super();
			this.value = value;
			this.currency = currency;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

	}

}
