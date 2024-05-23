package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name= "UsuarioDto", description = "Datos propios de la entidad Estudiante")
public class EstudianteDto {

    private Long idEstudiante;

    private Usuario usuario;

}
