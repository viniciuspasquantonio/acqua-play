package br.com.pasquantonio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pasquantonio.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

} 