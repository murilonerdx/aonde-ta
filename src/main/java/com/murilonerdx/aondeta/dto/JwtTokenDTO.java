package com.murilonerdx.aondeta.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenDTO{
    private String email;
    private String token;
}
