package br.com.pasquantonio.domain;

import java.math.BigDecimal;

public class SearchRequestDTO {
	private String search;
	private String category;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	
	public String getSearch() {
		return search;
	}
	public String getCategory() {
		return category;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
}
