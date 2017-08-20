package br.com.pasquantonio.controller;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.entity.Chat;
import br.com.pasquantonio.service.ChatService;

@RestController
@RequestMapping(value="/chat")
public class ChatController {
	
	@Autowired	
	private ChatService chatService;

	@RequestMapping(value="/")
	public Principal chat(Principal principal) {
		return principal;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Chat create(@RequestBody @Valid Chat chat) {
		return chatService.save(chat);
	}

	
	@RequestMapping(value = "/seller/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Chat> findBySeller(@PathVariable("sellerId") Long sellerId) {
		return chatService.findBySeller(sellerId);
	}
	
	
	
	@RequestMapping(value = "/findUserAdChat/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Chat> findByAdIdAndUserId(@RequestParam("adId") Long adId,@RequestParam("userId") Long userId) {
		return chatService.findByAdIdAndUserId(adId,userId);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
	public void handleNotFound() {}
}
