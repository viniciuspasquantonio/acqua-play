package br.com.pasquantonio.vinicius.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.pasquantonio.vinicius.entity.Ad;


@RepositoryRestResource
@CrossOrigin
public interface AdRepository extends PagingAndSortingRepository<Ad, Long> {


}