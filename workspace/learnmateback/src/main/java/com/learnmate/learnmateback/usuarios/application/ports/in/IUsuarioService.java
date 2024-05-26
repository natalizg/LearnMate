package com.learnmate.learnmateback.usuarios.application.ports.in;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ClaseDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ProfesorFilter;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.UsuarioDto;
import com.learnmate.learnmateback.usuarios.application.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IUsuarioService {

    Usuario loginUsuario(String email, String password);

    List<Usuario> getAllEstudiantesByidProfesor(Long idProfesor);

    List<Usuario> getAllProfesoresByIdEstudiante(Long idEstudiante);

    List<Clase> getAllClasesByIdProfesorOrIdEstudiante(Long idEstudiante, Long idProfesor);

    Usuario createUsuario(UsuarioDto usuario, String password);

    Usuario updateUsuario(UsuarioDto usuario, String password);

    Clase createClase(ClaseDto clase);

    Clase updateClase(ClaseDto clase);

    void deleteClase(Long idClase);

    List<Materia> getAllMaterias();

    List<TramoHorario> getAllTramosHorarios();

    Page<UsuarioDto> getAllProfesores(ProfesorFilter filter, Pageable pageable);

}
