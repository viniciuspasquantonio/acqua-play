package br.com.pasquantonio.vinicius.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.pasquantonio.vinicius.entity.Sale;


@RepositoryRestResource
public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {


}