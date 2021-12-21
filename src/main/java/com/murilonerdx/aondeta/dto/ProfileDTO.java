package com.murilonerdx.aondeta.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO extends RepresentationModel {
    @Mapping("id")
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
