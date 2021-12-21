package com.murilonerdx.aondeta.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDTO extends RepresentationModel<LocalDTO> {
    @Mapping("id")
    private Long id;
    @Size(min=8, message="O endereço tem que ter no minimo 8 caracters")
    private String address;

    @NotNull(message="A latitude não pode ser null")
    private Double latitude;

    @NotNull(message="A longitude não pode ser null")
    private Double longitude;
}
