package com.project.barista101.payload.request;

import com.project.barista101.data.ERoles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {
    private String email;
    private String fullname;
    private String username;
    private String password;
    private ERoles role;
}
