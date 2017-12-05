package br.com.pasquantonio.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.domain.SearchRequestDTO;
import br.com.pasquantonio.entity.Ad;
import br.com.pasquantonio.repository.AdRepository;
import br.com.pasquantonio.repository.AdRepositoryCustom;

@Service
public class AdService  {

    @Autowired
    private AdRepository adRepository;


	public Iterable<Ad> findAll() {
		return adRepository.findAll();
	}

	public Ad findOne(String id) {
		return adRepository.findOne(id);
	}

	public Ad save(Ad ad) {
		return adRepository.save(ad);
	}

	public void delete(String id) {
		adRepository.delete(id);
		
	}

	public Iterable<Ad> findBySellerId(String sellerId) {
		return adRepository.findBySellerId(sellerId);
	}

	public Page<Ad> findBy(String search, Pageable pageable) {
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(search);
		return adRepository.findBy(criteria, pageable);
	}

	public Iterable<Ad> findBySearchWhitinPrice(SearchRequestDTO searchRequestDTO, Pageable pageable) {
		if(searchRequestDTO.getMinPrice() == null){
			searchRequestDTO.setMinPrice(BigDecimal.ZERO);
		}
		if(searchRequestDTO.getMaxPrice() == null){
			searchRequestDTO.setMaxPrice(new BigDecimal(Long.MAX_VALUE));
		}
		return adRepository.findBySearchWhitinPrice(searchRequestDTO.getSearch(),searchRequestDTO.getMinPrice(),searchRequestDTO.getMaxPrice(),pageable);
	}

	public Page<Ad> findByText(String search, Pageable pageable) {
		return adRepository.findByText(search,pageable);
	}


}
