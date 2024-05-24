package com.learnmate.learnmateback.usuarios.adapter.out.repositories;

import com.learnmate.learnmateback.usuarios.application.domain.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

    List<Clase> findAllByProfesor_IdProfesor(Long idProfesor);

    List<Clase> findAllByEstudiante_IdEstudiante(Long idEstudiante);

}
