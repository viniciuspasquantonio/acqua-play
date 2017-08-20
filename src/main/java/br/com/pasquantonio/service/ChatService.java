package br.com.pasquantonio.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.entity.Chat;
import br.com.pasquantonio.repository.ChatRepository;

@Service
public class ChatService  {

    @Autowired
    private ChatRepository chatRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Chat save(Chat chat){
    	chat.setInstant(Instant.now());
        return chatRepository.save(chat);
    }
    
    
    public List<Chat> findByAdIdAndUserId(Long adId,Long userId){
        return chatRepository.findByAdIdAndUserId(adId, userId);
    }
    
    public List<Chat> findBySeller(Long sellerId){
        return chatRepository.findBySellerId(sellerId);
    }


}
