package br.com.pasquantonio.vinicius.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Interest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Person person;
	
	@OneToMany(mappedBy="interest")
	private List<Product> products;
	
	@OneToMany(mappedBy="interest")
	private List<Service> service;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}

}
