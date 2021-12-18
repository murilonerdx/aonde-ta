package com.murilonerdx.aondeta.dto;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    private Long id;

    @NotEmpty(message="O nome não pode estar vazio")
    private String name;

    @Email()
    @NotEmpty(message="Campo e-mail não pode estar vazio")
    private String email;

    @NotEmpty(message="O numero do telefone não pode estar vazio")
    private String phone;

    @CPF(message="Digite um cpf valido")
    private String cpf;

    private String description;

    private byte[] photo;
}
