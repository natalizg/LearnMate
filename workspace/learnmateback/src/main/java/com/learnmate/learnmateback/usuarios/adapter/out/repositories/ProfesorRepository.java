package com.learnmate.learnmateback.usuarios.adapter.out.repositories;

import com.learnmate.learnmateback.usuarios.application.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long>, JpaSpecificationExecutor<Profesor> {


}
