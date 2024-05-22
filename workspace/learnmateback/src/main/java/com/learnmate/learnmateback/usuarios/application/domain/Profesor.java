package com.learnmate.learnmateback.usuarios.application.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="PROFESORES")
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="ID_PROFESOR", nullable = false)
    private Long idProfesor;

    @Column(name="DESCRIPCION", nullable = false)
    @Length(max = 1000)
    private String descripcion;

    @Column(name="PRECIO", nullable = false)
    @Length(max = 2)
    private Integer precio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA")
    private Materia materia;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "REL_PROTRAHOR", joinColumns = @JoinColumn(name = "ID_PROFESOR", referencedColumnName = "ID_PROFESOR"),
            inverseJoinColumns =  @JoinColumn(name = "ID_TRAMO_HORARIO", referencedColumnName = "ID_TRAMO_HORARIO"))
    private List<TramoHorario> tramosHorarios;
    
}
