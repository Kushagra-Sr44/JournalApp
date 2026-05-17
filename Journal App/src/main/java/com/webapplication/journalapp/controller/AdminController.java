package com.webapplication.journalapp.controller;

import com.webapplication.journalapp.entity.User;
import com.webapplication.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/getall")
    public ResponseEntity<?> getAllUser(){
       List<User> list= userService.getAllUser();
       if(list!=null||!list.isEmpty()){
           return new ResponseEntity<>(list,HttpStatus.OK );
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.createAdmin(user);
    }
}
