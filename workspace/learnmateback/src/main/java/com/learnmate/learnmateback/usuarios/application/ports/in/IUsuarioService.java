package com.learnmate.learnmateback.usuarios.application.ports.in;

import com.learnmate.learnmateback.usuarios.application.domain.Clase;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario loginUser(String email, String password);

    List<Usuario> getAllAlumnosByidProfesor(Long idProfesor);

    List<Usuario> getAllProfesoresByIdEstudiante(Long idEstudiante);

    List<Clase> getAllClasesByIdProfesorOrIdEstudiante(Long idEstudiante, Long idProfesor);

}
