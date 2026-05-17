package com.webapplication.journalapp.controller;

import com.webapplication.journalapp.entity.User;
import com.webapplication.journalapp.repository.UserEntryRepository;
import com.webapplication.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserEntryRepository userEntryRepository;
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User userindb=userEntryRepository.findByUserName(username);
        userindb.setUserName(user.getUserName());
        userindb.setPassword(user.getPassword());
        userService.saveUserEntry(userindb);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
userEntryRepository.deleteByUserName(auth.getName());
return new ResponseEntity<>(HttpStatus.OK);
    }

}
