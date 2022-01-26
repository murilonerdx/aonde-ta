package com.murilonerdx.aondeta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dozermapper.core.Mapping;
import com.murilonerdx.aondeta.entities.Report;
import com.murilonerdx.aondeta.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProfileLogin implements Serializable {
    @NotEmpty(message="O nome não pode estar vazio")
    private String name;

    @NotEmpty(message="O numero do telefone não pode estar vazio")
    private String phone;

    @CPF(message="Digite um cpf valido")
    private String cpf;

    private String description;

    @JsonIgnore
    List<Long> reportsIds = new ArrayList<>();

    public void getIdByReportList(List<Report> reportsList){
        this.reportsIds = reportsList.stream().map(Report::getId).collect(Collectors.toList());
    }

    @Email
    @NotEmpty(message="E-mail não pode estar vazio")
    private String email;

    @NotEmpty(message="Password não pode estar vazio")
    @Size(min=8, message="A senha precisa ter pelo menos 8 caracteres")
    private String password;

    public AuthenticationDTO getAuthenticationDTO(){
        return new AuthenticationDTO(this.email, this.password);
    }

    public ProfileDTO getProfileDTO(){
        return new ProfileDTO(null,this.name, this.email,this.phone,this.cpf,this.description, this.reportsIds);
    }
}
