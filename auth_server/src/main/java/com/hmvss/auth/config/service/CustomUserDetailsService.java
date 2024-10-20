package com.hmvss.auth.config.service;

import com.hmvss.auth.persistence.model.User;
import com.hmvss.auth.persistence.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }



    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority>  authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority( user.getRole().getRoleName()));
        System.out.println("authorities:"+authorities.toString());
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("No User Found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                !user.isExpired(),  // not expired
                !user.isCredentialExpired(),  // credentials not expired
                !user.isLocked(),  // account not locked
                getAuthorities(user)
        );
    }
}
