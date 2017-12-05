package br.com.pasquantonio.domain;

import java.math.BigDecimal;
import java.util.List;

public class AdListPage {
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private List<String> adsIds;
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public List<String> getAdsIds() {
		return adsIds;
	}
	public void setAdsIds(List<String> adsIds) {
		this.adsIds = adsIds;
	}
}
