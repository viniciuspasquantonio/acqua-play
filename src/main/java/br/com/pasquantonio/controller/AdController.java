package br.com.pasquantonio.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.entity.Ad;
import br.com.pasquantonio.repository.AdRepository;

@RestController
@RequestMapping(value="/ads")
public class AdController {
	@Autowired	
	private AdRepository adRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Ad> findAll() {
		return adRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Ad findById(@PathVariable("id") Integer id) {
		return adRepository.findOne(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Ad create(@RequestBody @Valid Ad ad) {
		return adRepository.save(ad);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Ad update(@RequestBody @Valid Ad ad, @PathVariable Long id) {
		return adRepository.save(ad);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		adRepository.delete(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
	public void handleNotFound() {}
}
