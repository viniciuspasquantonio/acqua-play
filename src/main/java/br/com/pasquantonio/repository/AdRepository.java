package br.com.pasquantonio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.pasquantonio.entity.Ad;

@Repository
public interface AdRepository extends CrudRepository<Ad, Integer> {


} 