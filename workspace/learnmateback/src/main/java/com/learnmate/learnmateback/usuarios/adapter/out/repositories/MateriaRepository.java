package com.learnmate.learnmateback.usuarios.adapter.out.repositories;

import com.learnmate.learnmateback.usuarios.application.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {


}
