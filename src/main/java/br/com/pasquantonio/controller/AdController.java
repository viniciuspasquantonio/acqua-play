package br.com.pasquantonio.controller;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pasquantonio.domain.SearchRequestDTO;
import br.com.pasquantonio.entity.Ad;
import br.com.pasquantonio.service.AdService;

@RestController
@RequestMapping(value = "/ads")
public class AdController {
	@Autowired
	private AdService adService;

	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Ad> findAll() {
		return adService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Ad findById(@PathVariable("id") String id) {
		return adService.findOne(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Ad create(@RequestBody @Valid Ad ad) {
		return adService.save(ad);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Ad update(@RequestBody @Valid Ad ad) {
		return adService.save(ad);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		adService.delete(id);
	}

	@RequestMapping(value = "/findBySeller", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Ad> findBySeller(@RequestParam("sellerId") String sellerId) {
		return adService.findBySellerId(sellerId);
	}

	@RequestMapping(value = "/search/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Page<Ad> findBySearch(@RequestParam("search") String search,
			Pageable pageable) {
		return adService.findByText(search, pageable);
	}
	
	@RequestMapping(value = "/searchPrice/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Ad> findBySearchWhitinPrice(@RequestParam("search") String search, @RequestParam(value="minPrice", required=false) BigDecimal minPrice,
			@RequestParam(value= "maxPrice", required=false) BigDecimal maxPrice , @RequestParam("category") String category,
			Pageable pageable) {
		SearchRequestDTO searchRequestDTO = createSearchRequestDTO(search, minPrice, maxPrice, category);
		return adService.findBySearchWhitinPrice(searchRequestDTO, pageable);
	}

	private SearchRequestDTO createSearchRequestDTO(String search, BigDecimal minPrice, BigDecimal maxPrice, String category) {
		SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
		searchRequestDTO.setSearch(search);
		searchRequestDTO.setCategory(category);
		searchRequestDTO.setMinPrice(minPrice);
		searchRequestDTO.setMaxPrice(maxPrice);
		return searchRequestDTO;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { EmptyResultDataAccessException.class, EntityNotFoundException.class })
	public void handleNotFound() {
	}
}
