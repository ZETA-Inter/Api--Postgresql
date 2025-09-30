package com.example.Api_Postgresql.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "field 'email' is null")
    private String email;

    @NotNull(message = "field 'password' is null")
    private String password;
    // getters e setters
}