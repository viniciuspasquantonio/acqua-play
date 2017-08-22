package br.com.pasquantonio.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.entity.Category;
import br.com.pasquantonio.repository.CategoryRepository;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired	
	private CategoryRepository categoryRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
	public void handleNotFound() {}
}
