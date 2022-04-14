package com.cjss.eCommerce1.configuration;

import com.cjss.eCommerce1.entity.UserEntity;
import com.cjss.eCommerce1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    public Integer id;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email);
        id = userEntity.getId();
        if(userEntity == null)
            throw  new UsernameNotFoundException("USER NOT FOUND");
        return new MyUserDetails(userEntity);
    }
}
