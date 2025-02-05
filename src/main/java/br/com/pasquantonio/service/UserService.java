package br.com.pasquantonio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pasquantonio.entity.User;
import br.com.pasquantonio.repository.UserRepository;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public User save(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User update(User user){
    	User savedUser = userRepository.findByEmail(user.getEmail());
        return userRepository.save(user);
    }
    
    public User findOne(Long id){
        return userRepository.findOne(id);
    }

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUserId(Long userId) {
		return userRepository.findOne(userId);
	}

}
