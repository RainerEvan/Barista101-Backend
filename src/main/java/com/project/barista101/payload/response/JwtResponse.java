package com.project.barista101.payload.response;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Date expirationDate;
    private UUID accountId;
    private String username;
    private String email;
    private String role;
}
