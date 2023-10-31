package com.azry.LMS.model.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    private String username;
    private String password;
   // private boolean isAdmin;
}

