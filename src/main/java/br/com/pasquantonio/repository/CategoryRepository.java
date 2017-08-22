package br.com.pasquantonio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pasquantonio.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

} 