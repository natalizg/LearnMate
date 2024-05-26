package com.learnmate.learnmateback.usuarios.application.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Entity
@Table(name="TRAMOS_HORARIOS")
public class TramoHorario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGenTramoHorario")
    @SequenceGenerator(name = "SeqGenTramoHorario", sequenceName = "SQ_TRAMOS_HORARIOS", allocationSize = 1)
    @Column(name="ID_TRAMO_HORARIO", nullable = false)
    private Long idTramoHorario;

    @Column(name="DESCRIPCION", nullable = false)
    @Length(max = 100)
    private String descripcion;
    
}
