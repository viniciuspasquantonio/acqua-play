package br.com.pasquantonio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pasquantonio.entity.Ad;

@Repository
public interface AdRepository extends MongoRepository<Ad, String>, AdRepositoryCustom {
	Iterable<Ad> findBySellerId(String sellerId);
	
	Page<Ad> findBy(TextCriteria criteria, Pageable page);

	@Query("{$or:[{'title':{$regex:?0,$options:'i'}},{'description':{$regex:?0,$options:'i'}}]}")
	Page<Ad> findByText(String search,Pageable pageable);



} 