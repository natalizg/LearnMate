package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import com.learnmate.learnmateback.usuarios.application.domain.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name= "ClaseDto", description = "Datos propios de la entidad Clase")
public class ClaseDto {

    private Long idClase;

    private Date fecha;

    private Estudiante estudiante;

    private Profesor profesor;

    private TramoHorario tramoHorario;

    private Materia materia;

}
