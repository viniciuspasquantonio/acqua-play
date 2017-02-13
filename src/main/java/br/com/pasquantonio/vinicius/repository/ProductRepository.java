package br.com.pasquantonio.vinicius.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.pasquantonio.vinicius.entity.Product;

@RepositoryRestResource
@CrossOrigin
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}