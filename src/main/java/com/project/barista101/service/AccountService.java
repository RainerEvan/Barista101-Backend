package com.project.barista101.service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.account.Roles;
import com.project.barista101.payload.request.ChangePasswordRequest;
import com.project.barista101.payload.request.SignupRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.RoleRepository;
import com.project.barista101.utils.ProfileImageUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
    
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<Accounts> getAllAccounts(){
        return accountRepository.findAll();
    }

    @Transactional
    public Accounts getAccount(UUID accountId){
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));
    }

    @Transactional
    public Accounts addAccount(SignupRequest accountRequest) {

        String username = accountRequest.getUsername();
        String email = accountRequest.getEmail();
        
        if(accountRepository.existsByUsername(username)){
            throw new IllegalStateException("Account with current username already exists: "+username);
        }

        if(accountRepository.existsByEmail(email)){
            throw new IllegalStateException("Account with current email already exists: "+email);
        }

        Roles role = roleRepository.findByName(accountRequest.getRole())
            .orElseThrow(() -> new IllegalStateException("Role with current name cannot be found: "+accountRequest.getRole()));

        Accounts account = new Accounts();
        account.setUsername(username);
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setFullname(accountRequest.getFullname());
        account.setRole(role);
        account.setProfileImg(addImage(null));

        return accountRepository.save(account);
    }

    @Transactional
    public Accounts editAccount(MultipartFile file, UUID accountId, SignupRequest accountRequest) {
        
        Accounts account = getAccount(accountId);

        String fullname = accountRequest.getFullname();

        if(fullname != null && fullname.length() > 0 && !Objects.equals(account.getFullname(), fullname)){
            account.setFullname(fullname);
        }

        if(file != null){
            account.setProfileImg(addImage(file));
        }

        return accountRepository.save(account);
    }

    @Transactional
    public void changePassword(ChangePasswordRequest changePasswordRequest){
        
        Accounts account = getAccount(changePasswordRequest.getAccountId());

        if(!passwordEncoder.matches(changePasswordRequest.getCurrPassword(), account.getPassword())){
            throw new IllegalStateException("The current password is not correct");
        }

        if(changePasswordRequest.getNewPassword() != null && changePasswordRequest.getNewPassword().length() > 0){
            account.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        }

        accountRepository.save(account);
    }

    public String addImage(MultipartFile file){
        try{
            byte[] image = new byte[0];
            
            if(file == null){
                File defaultImg = new File("src/main/resources/image/profile.jpg");
                image = FileUtils.readFileToByteArray(defaultImg);
            } else {
                image = ProfileImageUtils.cropImageSquare(file);
            }

            String encodedString = Base64.getEncoder().encodeToString(image);

            return encodedString;
            
        } catch (IOException exception){
            throw new IllegalStateException("Could not add the current file", exception);
        }
    }
    
    public byte[] getProfileImage(UUID accountId){

        Accounts account = getAccount(accountId);

        byte[] decodedBytes = Base64.getDecoder().decode(account.getProfileImg());

        return decodedBytes;
    }
}
