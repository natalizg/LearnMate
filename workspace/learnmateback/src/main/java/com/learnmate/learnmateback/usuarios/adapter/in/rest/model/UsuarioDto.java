package com.learnmate.learnmateback.usuarios.adapter.in.rest.model;

import lombok.Data;

@Data
//@Schema(name= "UsuarioDto", description = "Datos propios de la entidad Usuario")
public class UsuarioDto {

    private Long idUsuario;

    private String email;

    private String nombre;

    private String apellidos;

    private Integer telefono;

}
