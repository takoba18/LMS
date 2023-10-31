package com.azry.LMS.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    private String username;
    private String password;
}

