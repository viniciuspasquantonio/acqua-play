package br.com.pasquantonio.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.pasquantonio.domain.AdListPage;
import br.com.pasquantonio.entity.Ad;

public class AdRepositoryImpl implements AdRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public AdRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<AdListPage> aggregate(String search, BigDecimal minPrice, BigDecimal maxPrice) {
		MatchOperation matchOperation = getMatchOperation(search, minPrice, maxPrice);
		GroupOperation groupOperation = getGroupOperation();
		ProjectionOperation projectionOperation = getProjectOperation();
		return mongoTemplate.aggregate(Aggregation.newAggregation(matchOperation, groupOperation, projectionOperation),
				Ad.class, AdListPage.class).getMappedResults();

	}

	private MatchOperation getMatchOperation(String search, BigDecimal minPrice, BigDecimal maxPrice) {
		Criteria criteria = getCriteria(search, minPrice, maxPrice);
		return match(criteria);
	}

	private Criteria getCriteria(String search, BigDecimal minPrice, BigDecimal maxPrice) {

		return where("title").regex(search).orOperator(where("description").regex(search))
				.andOperator(where("price").gte(minPrice).andOperator(where("price").lte(maxPrice)));
	}

	private GroupOperation getGroupOperation() {
		return group("prices").min("price").as("minPrice").max("price").as("maxPrice").addToSet("id").as("adsIds");
	}

	private ProjectionOperation getProjectOperation() {
		return project("ads", "minPrice", "maxPrice").and("prices").previousOperation();
	}

	@Override
	public List<Ad> findByBetween(String search, BigDecimal minPrice, BigDecimal maxPrice) {
		Query query = new Query();
		query.addCriteria(getCriteria(search, minPrice, maxPrice));
		return mongoTemplate.find(query, Ad.class);
	}

	@Override
	public List<Ad> findBySearchWhitinPrice(String search, BigDecimal minPrice, BigDecimal maxPrice,
			Pageable pageable) {

		Query query = new Query();
		query.addCriteria(where("price").lte(maxPrice).gte(minPrice)
				.andOperator(where("title").regex(search).orOperator(where("description").regex(search))));
		query.with(pageable);
		return mongoTemplate.find(query, Ad.class);
	}

}
