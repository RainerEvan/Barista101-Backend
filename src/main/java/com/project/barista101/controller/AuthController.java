package com.project.barista101.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.payload.request.SigninRequest;
import com.project.barista101.payload.request.SignupRequest;
import com.project.barista101.payload.response.JwtResponse;
import com.project.barista101.service.AccountService;
import com.project.barista101.service.AuthService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private final AccountService accountService;

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody SigninRequest signinRequest){
        try {
            JwtResponse response = authService.signinAccount(signinRequest);
            
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
    
    @PostMapping(path = "/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest){
        try{
            Accounts account = accountService.addAccount(signupRequest);

            return ResponseHandler.generateResponse("Account has been created successfully!", HttpStatus.OK, account.getUsername());
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
