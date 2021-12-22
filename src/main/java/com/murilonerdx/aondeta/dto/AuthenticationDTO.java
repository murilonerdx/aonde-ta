package com.murilonerdx.aondeta.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDTO{
    @Email
    @NotEmpty(message="E-mail não pode estar vazio")
    private String email;

    @NotEmpty(message="Password não pode estar vazio")
    @Size(min=8, message="A senha precisa ter pelo menos 8 caracteres")
    private String password;
}
