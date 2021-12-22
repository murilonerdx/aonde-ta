package com.murilonerdx.aondeta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dozermapper.core.Mapping;
import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.entities.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO extends RepresentationModel<ProfileDTO> implements Serializable {
    @Mapping("id")
    private Long id;

    @NotEmpty(message="O nome não pode estar vazio")
    private String name;

    @Email()
    @NotEmpty(message="Campo e-mail não pode estar vazio")
    private String email;

    @NotEmpty(message="Campo senha não pode estar vazio")
    private String password;

    @NotEmpty(message="O numero do telefone não pode estar vazio")
    private String phone;

    @CPF(message="Digite um cpf valido")
    private String cpf;

    private String description;

    private byte[] photo;

    List<Long> reportsIds = new ArrayList<>();

    public void getIdByReportList(List<Report> reportsList){
        this.reportsIds = reportsList.stream().map(Report::getId).collect(Collectors.toList());
    }
}
