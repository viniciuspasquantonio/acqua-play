package br.com.pasquantonio.vinicius.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Interest interest;
	
	@ManyToOne
	private Sale sale;
	
	@ManyToOne
	private Purchase purchase;

	@ManyToOne
	private ProductType productType;

	public Interest getInterest() {
		return interest;
	}

	public void setInterest(Interest interest) {
		this.interest = interest;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	

}
