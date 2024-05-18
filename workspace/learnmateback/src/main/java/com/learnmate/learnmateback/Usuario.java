package com.learnmate.learnmateback;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name="EMAIL", nullable = false)
    @Length(max = 200)
    private String email;

    @Column(name="NOMBRE", nullable = false)
    @Length(max = 40)
    private String nombre;

    @Length(max = 100)
    @Column(name="APELLIDOS", nullable = false)
    private String apellidos;

    @Length(min= 9, max = 9)
    @Column(name="TELEFONO", nullable = false)
    private Integer telefono;
}
