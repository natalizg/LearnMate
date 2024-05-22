package com.learnmate.learnmateback.usuarios.application.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Entity
@Table(name="MATERIAS")
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="ID_MATERIA", nullable = false)
    private Long idMateria;

    @Column(name="NOMBRE", nullable = false)
    @Length(max = 100)
    private String nombre;

    @Column(name="COLOR", nullable = false)
    @Length(max = 6)
    private String color;
    
}
