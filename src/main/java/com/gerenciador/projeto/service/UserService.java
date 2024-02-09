package com.gerenciador.projeto.service;

import com.gerenciador.projeto.model.User;
import com.gerenciador.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(long id, User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found for this id::"+id));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setNickname(user.getNickname());
        return userRepository.save(user);
    }

    public void deleteUser(long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found for this id::"+id));
        userRepository.delete(user);
    }

}
