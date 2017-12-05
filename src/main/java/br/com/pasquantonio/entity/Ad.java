package br.com.pasquantonio.entity;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ad {
	@Id
	private String id;
	
	private BigDecimal price;
	@TextIndexed(weight=3)
	private String title;
	@TextIndexed
	private String description;
	private String sellerId;
	
	private Collection<String> images;
	
	private AdEnum type;
	
	private String categoryId;
	@TextIndexed(weight=2)
	private String keyWords;

	@TextIndexed(weight=2)
	private String category;
	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AdEnum getType() {
		return type;
	}

	public void setType(AdEnum type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<String> getImages() {
		return images;
	}

	public void setImages(Collection<String> images) {
		this.images = images;
	}


	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}



}
