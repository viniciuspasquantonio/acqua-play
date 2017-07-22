package br.com.pasquantonio.controller;

import java.security.Principal;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.entity.User;
import br.com.pasquantonio.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired	
	private UserService userService;

	@RequestMapping(value="/")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody @Valid User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/findByUsername/{username}", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public User findById(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
	public void handleNotFound() {}
}
