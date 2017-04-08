package br.com.pasquantonio.vinicius.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ad {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private BigDecimal price;
	private String title;
	private String description;
	
	@Enumerated()
	private AdEnum type;
	
	@ManyToOne
	private Person person;
	
	@OneToMany(mappedBy="ad")
	private List<Product> products;
	
	@OneToMany(mappedBy="ad")
	private List<Service> services;
	
	@OneToMany(mappedBy="ad")
	private List<Interest> interests;


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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}


}
