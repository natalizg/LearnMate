package com.learnmate.learnmateback.usuarios.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenUsuario")
    @SequenceGenerator(name = "SeqGenUsuario", sequenceName = "SQ_USUARIOS", allocationSize = 1)
    @Column(name="ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name="EMAIL", nullable = false)
    @Length(max = 200)
    private String email;

    @Column(name="NOMBRE", nullable = false)
    @Length(max = 40)
    private String nombre;

    @Column(name="APELLIDOS", nullable = false)
    @Length(max = 100)
    private String apellidos;

    @Column(name="TELEFONO", nullable = false)
    @Min(111111111)
    @Max(999999999)
    private Integer telefono;

    @Column(name="PASSWORD", nullable = false)
    @Length(max = 1000)
    private String password;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Estudiante estudiante;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profesor profesor;

}
