package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name= "MateriaDto", description = "Datos propios de la entidad Materia")
public class MateriaDto {

    private Long idMateria;

    private String nombre;

    private String color;

}
