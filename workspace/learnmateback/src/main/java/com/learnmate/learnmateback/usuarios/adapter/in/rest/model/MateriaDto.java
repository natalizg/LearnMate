package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import com.learnmate.learnmateback.usuarios.application.domain.Materia;
import com.learnmate.learnmateback.usuarios.application.domain.TramoHorario;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
//@Schema(name= "MateriaDto", description = "Datos propios de la entidad Materia")
public class MateriaDto {

    private Long idMateria;

    private String nombre;

    private String color;

}
