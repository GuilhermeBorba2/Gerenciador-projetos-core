package com.gerenciador.projeto.service;

import com.gerenciador.projeto.model.DTO.UserDto;
import com.gerenciador.projeto.model.User;
import com.gerenciador.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser (UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
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

    public User updateUser(long id, UserDto userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found for this id::"+id));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());

        if(userDetails.getNewPassword() !=null && !userDetails.getNewPassword().trim().isEmpty()){
            user.setPassword(passwordEncoder.encode(userDetails.getNewPassword()));
        }

        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
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
