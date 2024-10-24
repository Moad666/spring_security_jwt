package com.example.demo.security.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repository.AdminRepository;

// allows Spring Security to query your database and  fetch a User entity using the username provided in the login form.


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AdminRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));

        return UserDetailsImpl.build(user);
    }


}

