package br.com.pasquantonio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.pasquantonio.entity.Chat;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
	List<Chat> findByAdIdAndUserId(Long adId,Long userId);
	List<Chat> findByAdId(Long adId);
	List<Chat> findBySellerId(Long sellerId);
}
