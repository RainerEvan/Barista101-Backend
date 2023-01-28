package com.project.barista101.security.details;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts account = new Accounts();

        if(username.contains("@")){
            account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email cannot be found"));
        }
        else{
            account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username cannot be found"));
        }

        return UserDetailsImpl.build(account);
    }

    
}
