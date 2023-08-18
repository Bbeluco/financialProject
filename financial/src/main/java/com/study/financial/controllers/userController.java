package com.study.financial.controllers;

import com.study.financial.DTO.UserCompleteInfoDTO;
import com.study.financial.entities.UserEntity;
import com.study.financial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserCompleteInfoDTO> getAllInfoAboutUser(@PathVariable int userId){
        UserEntity user = userRepository.findById(userId).get();
        return ResponseEntity.ok(new UserCompleteInfoDTO(user));
    }
}
