package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
//@Schema(name= "TramoHorarioDto", description = "Datos propios de la entidad TramoHorario")
public class TramoHorarioDto {

    private Long idTramoHorario;

    private String descripcion;

}
