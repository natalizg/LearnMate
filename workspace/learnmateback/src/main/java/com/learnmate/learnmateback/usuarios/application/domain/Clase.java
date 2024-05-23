package com.learnmate.learnmateback.usuarios.application.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="CLASES")
public class Clase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="ID_CLASE", nullable = false)
    private Long idClase;

    @Column(name="FECHA", nullable = false)
    private Date fecha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    private Estudiante estudiante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROFESOR", referencedColumnName = "ID_PROFESOR")
    private Profesor profesor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TRAMO_HORARIO", referencedColumnName = "ID_TRAMO_HORARIO")
    private TramoHorario tramoHorario;

    @OneToOne
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA")
    private Materia materia;
    
}
