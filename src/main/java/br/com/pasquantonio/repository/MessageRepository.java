package br.com.pasquantonio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pasquantonio.entity.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
	List<Message> findByAdIdAndUserId(Long adId,Long userId);
	List<Message> findByAdId(Long adId);
	List<Message> findBySellerId(Long sellerId);
	List<Message> findByUserIdOrSellerId(Long userId,Long sellerId);
}
