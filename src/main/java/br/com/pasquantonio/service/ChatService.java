package br.com.pasquantonio.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.entity.Ad;
import br.com.pasquantonio.entity.Chat;
import br.com.pasquantonio.entity.ChatKey;
import br.com.pasquantonio.entity.Message;
import br.com.pasquantonio.repository.AdRepository;
import br.com.pasquantonio.repository.MessageRepository;
import br.com.pasquantonio.repository.UserRepository;

@Service
public class ChatService  {

    @Autowired
    private MessageRepository chatRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AdRepository adRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Message saveMessage(Message message){
    	message.setInstant(Instant.now());
    	message.setChatKey(new ChatKey(message).hashCode());
        return chatRepository.save(message);
    }
    
    
    public List<Message> findByAdIdAndUserId(Long adId,Long userId){
        return chatRepository.findByAdIdAndUserId(adId, userId);
    }
    
    public List<Message> findBySeller(Long sellerId){
        return chatRepository.findBySellerId(sellerId);
    }

    public List<Chat> listByUser(Long userId){
    	List<Message> userMessages = chatRepository.findByUserIdOrSellerId(userId,userId);
    	return convertToChats(userMessages, userId);
    }

	private List<Chat> convertToChats(List<Message> userMessages , Long userId) {
		Map<ChatKey, List<Message>> chatMessagesMap = new HashMap<>();
		for (Message message : userMessages) {
			ChatKey key = new ChatKey(message);
			List<Message> messages = new ArrayList<>();
			if(chatMessagesMap.containsKey(key)){
				messages = chatMessagesMap.get(key);
			}
			messages.add(message);
			chatMessagesMap.put(key, messages);
		}
		List<Chat> chats = new ArrayList<>();
		for (Entry<ChatKey, List<Message>> entrySet : chatMessagesMap.entrySet()) {
			List<Message> messages = entrySet.getValue();
			if(messages == null|| messages.isEmpty()){
				continue;
			}
			
			Chat chat = new Chat(entrySet.getKey().hashCode(),entrySet.getValue());
			if(entrySet.getKey().getUserId().equals(userId)){
				chat.setUser1(userRepository.findOne(entrySet.getKey().getUserId()));
				chat.setUser2(userRepository.findOne(entrySet.getKey().getSellerId()));
			}else{
				chat.setUser2(userRepository.findOne(entrySet.getKey().getUserId()));
				chat.setUser1(userRepository.findOne(entrySet.getKey().getSellerId()));
				
			}
			Ad ad = adRepository.findOne(entrySet.getKey().getAdId());
			chat.setAd(ad);
			
			chats.add(chat);
		}
		return chats;
	}


}
