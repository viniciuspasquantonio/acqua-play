package br.com.pasquantonio.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.pasquantonio.domain.AdListPage;
import br.com.pasquantonio.entity.Ad;

public interface AdRepositoryCustom {

    List<AdListPage> aggregate(String search, BigDecimal minPrice, BigDecimal maxPrice);
    List<Ad> findByBetween(String search, BigDecimal minPrice, BigDecimal maxPrice);
    List<Ad> findBySearchWhitinPrice(String search, BigDecimal minPrice, BigDecimal maxPrice,Pageable pageable);

}
