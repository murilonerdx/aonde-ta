package com.murilonerdx.aondeta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ReportDTO {
    private Long id;
    @NotEmpty(message="Digite o nome do lugar, avenida, ou rua relacionado a esse evento")
    private String name;

    @Size(min=10, message="Digite um descrição para sabermos o local exato que isso aconteceu")
    private String description;

    @JsonFormat(pattern="dd-MM-YYYY")
    private LocalDate momentEvent;

    @Size(min=10, message="Digite pelo menos dez caracters, e descrições do que aconteceu")
    private String whatWasStolen;
}
