package com.miniproject.aeon.service;

import com.miniproject.aeon.domain.dao.Users;
import com.miniproject.aeon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.getDistinctTopByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Username Not Found");
        }
        return user;

    }
}
