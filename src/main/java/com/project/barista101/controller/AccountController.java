package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.payload.request.ChangePasswordRequest;
import com.project.barista101.payload.request.SignupRequest;
import com.project.barista101.service.AccountService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    
    @Autowired
    private final AccountService accountService;

    @PutMapping(path = "/edit")
    public ResponseEntity<Object> editAccount(@RequestPart(name = "image", required = false) MultipartFile image, @RequestPart("accountId") UUID accountId, @RequestPart("account") SignupRequest accountRequest){
        try{
            Accounts account = accountService.editAccount(image, accountId, accountRequest);

            return ResponseHandler.generateResponse("Account has been updated successfully!", HttpStatus.OK, account.getUsername());

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping(path = "/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        try{
            accountService.changePassword(changePasswordRequest);

            return ResponseHandler.generateResponse("Password has been updated successfully", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping(path = "/profile-img/{accountId}")
    public byte[] getProfileImg(@PathVariable("accountId") UUID accountId){
        return accountService.getProfileImage(accountId);
    }
}
