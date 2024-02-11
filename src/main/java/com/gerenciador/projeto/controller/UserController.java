package com.gerenciador.projeto.controller;


import com.gerenciador.projeto.model.DTO.UserDto;
import com.gerenciador.projeto.model.User;
import com.gerenciador.projeto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService =userService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getIdByID(@PathVariable Long id){
        User user = userService.findUserById(id)
            .orElseThrow(()-> new RuntimeException("User not found for this id::"+id));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/`{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Validated  UserDto userDetails){
        User updateUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        return ResponseEntity.ok(user);
    }
}



