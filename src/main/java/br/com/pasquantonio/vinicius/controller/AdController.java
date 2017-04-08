package br.com.pasquantonio.vinicius.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.vinicius.entity.Ad;
import br.com.pasquantonio.vinicius.repository.AdRepository;


@RestController
@RequestMapping(value="/ads")
public class AdController {
	@Autowired	
	private AdRepository adRepository;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET, consumes="application/json",produces="application/json")
	public ResponseEntity<Iterable<Ad>> retrivePastSixtySecondsStatistics() {
		return new ResponseEntity<>(adRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Ad> create(@RequestBody @Valid Ad ad) {
		return new ResponseEntity<>(adRepository.save(ad),HttpStatus.CREATED);
	}
}
