package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name= "TramoHorarioDto", description = "Datos propios de la entidad TramoHorario")
public class TramoHorarioDto {

    private Long idTramoHorario;

    private String descripcion;

}
