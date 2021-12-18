package com.murilonerdx.aondeta.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalDTO {
    private Long id;
    @Size(min=8, message="O endereço tem que ter no minimo 8 caracters")
    private String address;

    @NotNull(message="A latitude não pode ser null")
    private Double latitude;

    @NotNull(message="A longitude não pode ser null")
    private Double longitude;
}
