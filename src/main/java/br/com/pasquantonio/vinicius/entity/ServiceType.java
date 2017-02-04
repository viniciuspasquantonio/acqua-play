package br.com.pasquantonio.vinicius.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ServiceType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
