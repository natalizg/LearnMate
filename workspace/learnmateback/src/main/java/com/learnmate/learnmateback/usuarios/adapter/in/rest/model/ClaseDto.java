package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import com.learnmate.learnmateback.usuarios.application.domain.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
//@Schema(name= "ClaseDto", description = "Datos propios de la entidad Clase")
public class ClaseDto {

    private Long idClase;

    private Date fecha;

    private Estudiante estudiante;

    private Profesor profesor;

    private TramoHorario tramoHorario;

    private Materia materia;

}
