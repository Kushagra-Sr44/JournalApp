package com.webapplication.journalapp.controller;

import com.webapplication.journalapp.entity.User;
import com.webapplication.journalapp.service.UserDetailServiceImp;
import com.webapplication.journalapp.service.UserService;
import com.webapplication.journalapp.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImp userDetailsServiceImp;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/health")
    public String HealthCheck() {
        return "ok";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return userService.saveUserEntry(user);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
               UserDetails userDetails=userDetailsServiceImp.loadUserByUsername(user.getUserName());
               String jwt=jwtUtil.generateToken(userDetails.getUsername());
               return new ResponseEntity<>(jwt,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error Occurred while creating authentication token "+e.getMessage());
            return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
        }
    }

}
