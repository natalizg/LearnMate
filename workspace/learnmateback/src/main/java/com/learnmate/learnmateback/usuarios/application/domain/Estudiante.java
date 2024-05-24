package com.learnmate.learnmateback.usuarios.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Entity
@Table(name="ESTUDIANTES")
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="ID_ESTUDIANTE", nullable = false)
    private Long idEstudiante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @JsonIgnore
    private Usuario usuario;
    
}
