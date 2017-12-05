package br.com.pasquantonio.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.entity.Chat;
import br.com.pasquantonio.entity.Message;
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
	public Message create(@RequestBody @Valid Message chat) {
		return chatService.saveMessage(chat);
	}

	
	@RequestMapping(value = "/seller/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Message> findBySeller(@PathVariable("sellerId") Long sellerId) {
		return chatService.findBySeller(sellerId);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Chat> findByuser(@RequestParam("userId") Long userId) {
		return chatService.listByUser(userId);
	}
	
	
	
	@RequestMapping(value = "/findUserAdChat/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<Message> findByAdIdAndUserId(@RequestParam("adId") Long adId,@RequestParam("userId") Long userId) {
		return chatService.findByAdIdAndUserId(adId,userId);
	}
	
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
//	public void handleNotFound() {}
}
