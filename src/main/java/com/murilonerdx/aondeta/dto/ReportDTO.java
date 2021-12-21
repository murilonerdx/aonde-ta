package com.murilonerdx.aondeta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO extends RepresentationModel<ReportDTO> {
    @Mapping("id")
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
