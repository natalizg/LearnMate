package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import com.learnmate.learnmateback.usuarios.application.domain.Materia;
import com.learnmate.learnmateback.usuarios.application.domain.TramoHorario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name= "ProfesorFilter", description = "Se utiliza para hacer el filtrado al rescatar todos los profesores")
public class ProfesorFilter {

    private String nombre;

    private Integer precioMin;

    private Integer precioMax;

    private Long idMateria;

    private List<Long> idsTramosHorarios;

}
