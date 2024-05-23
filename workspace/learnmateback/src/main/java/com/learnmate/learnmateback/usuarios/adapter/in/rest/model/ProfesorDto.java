package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import com.learnmate.learnmateback.usuarios.application.domain.Materia;
import com.learnmate.learnmateback.usuarios.application.domain.TramoHorario;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name= "ProfesorDto", description = "Datos propios de la entidad Profesor")
public class ProfesorDto {

    private Long idProfesor;

    private String descripcion;

    private Integer precio;

    //private Usuario usuario;

    private Materia materia;

    private List<TramoHorario> tramosHorarios;

}
