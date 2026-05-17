package com.webapplication.journalapp.service;

import com.webapplication.journalapp.entity.User;
import com.webapplication.journalapp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> saveUserEntry(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(List.of("USER"));
        userEntryRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
public List<User> getAllUser() {
    return userEntryRepository.findAll();
}
public User findUserByUsername(String username) {
        return userEntryRepository.findByUserName(username);
}
public ResponseEntity<?> createAdmin( User user) {
       if(user!=null){
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(List.of("ADMIN"));
           userEntryRepository.save(user);
           return new ResponseEntity<>( HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


}
    public ResponseEntity<?> saveEntry(User user) {
        if (user == null || user.getUserName() == null || user.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (userEntryRepository.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        try {
            userEntryRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public List<User> getAll() {
        return userEntryRepository.findAll();
    }

    public Optional<User> getById(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userEntryRepository.deleteById(id);
    }

    public ResponseEntity<?> setUser(User newUser, String username) {
        User olduser = userEntryRepository.findByUserName(username);
        if (olduser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (newUser == null || newUser.getUserName() == null || newUser.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User existingUser = userEntryRepository.findByUserName(newUser.getUserName());
        if (existingUser != null && !existingUser.getId().equals(olduser.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        olduser.setUserName(newUser.getUserName());
        olduser.setPassword(newUser.getPassword());
        try {
            userEntryRepository.save(olduser);
            return new ResponseEntity<>(olduser, HttpStatus.OK);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
