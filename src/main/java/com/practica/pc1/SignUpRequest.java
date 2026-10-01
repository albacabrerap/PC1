package com.practica.pc1;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Roles role;
}