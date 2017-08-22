package br.com.pasquantonio.entity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

public class Message {

	private static final String DD_MM_YYYY_HH_MM = "dd-MM-yyyy HH:mm";

	private Long id;
	
	private Long adId;
	private Long userId;
	private Long sellerId;
	
	private String text;
	private Instant instant;
	
	private String username;
	
	private String timeSent;
	
	private Integer chatKey;

	
	Message() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	
	public String getTimeSent() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY_HH_MM)
	            .withZone( ZoneId.systemDefault() );
		return formatter.format(instant);
	}


	public Integer getChatKey() {
		return chatKey;
	}

	public void setChatKey(Integer chatKey) {
		this.chatKey = chatKey;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", adId=");
		builder.append(adId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", sellerId=");
		builder.append(sellerId);
		builder.append(", text=");
		builder.append(text);
		builder.append(", instant=");
		builder.append(instant);
		builder.append(", username=");
		builder.append(username);
		builder.append(", timeSent=");
		builder.append(timeSent);
		builder.append(", chatKey=");
		builder.append(chatKey);
		builder.append("]");
		return builder.toString();
	}





	
}