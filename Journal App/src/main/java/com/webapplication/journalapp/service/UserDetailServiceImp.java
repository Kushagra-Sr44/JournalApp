package com.webapplication.journalapp.service;

import com.webapplication.journalapp.entity.User;
import com.webapplication.journalapp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntryRepository.findByUserName(username);
        if (user != null) {
            UserDetails userDetails =org.springframework.security.core.userdetails.User.builder().
                    username(user.getUserName())
                            .password(user.getPassword()).
                    roles(user.getRoles().toArray(new String[0])).
                    build();
            return userDetails;
        }
        throw new UsernameNotFoundException("Username not found" + username);
    }


}
