package br.com.pasquantonio.entity;

import java.util.List;

public class Chat {

	private List<Message> messages;
	private int chatKey;
	private User user1;
	private User user2;
	private Ad ad;
	private String adImage;
	
	public Chat(Integer chatKey, List<Message> messages) {
		this.chatKey = chatKey;
		this.messages = messages;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public int getChatKey() {
		return chatKey;
	}
	public void setChatKey(int chatKey) {
		this.chatKey = chatKey;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public Ad getAd() {
		return ad;
	}
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	public String getAdImage() {
		return adImage;
	}
	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}
}
