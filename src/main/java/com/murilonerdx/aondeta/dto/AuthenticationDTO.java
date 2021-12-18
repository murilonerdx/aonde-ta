package com.murilonerdx.aondeta.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDTO {
    private String email;
    private String password;
}
