package br.com.pasquantonio.vinicius.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.pasquantonio.vinicius.entity.Ad;

@CrossOrigin
@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {


}