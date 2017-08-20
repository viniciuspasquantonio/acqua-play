package br.com.pasquantonio.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chat {

	@Id
	@GeneratedValue
	private String id;
	
	private Long adId;
	private Long userId;
	private Long sellerId;
	
	private String message;
	private Instant instant;
	
	private String username;
	
	Chat() {
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}
	

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chat [id=");
		builder.append(id);
		builder.append(", adId=");
		builder.append(adId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", sellerId=");
		builder.append(sellerId);
		builder.append(", message=");
		builder.append(message);
		builder.append(", instant=");
		builder.append(instant);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
	

	
}