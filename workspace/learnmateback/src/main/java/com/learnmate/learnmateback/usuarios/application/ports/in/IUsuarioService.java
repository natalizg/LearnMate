package com.learnmate.learnmateback.usuarios.application.ports.in;

import com.learnmate.learnmateback.usuarios.application.domain.Clase;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario loginUser(String email, String password);

    List<Usuario> getAlumnos(Long idProfesor);

    List<Usuario> getProfesores(Long idEstudiante);

    List<Clase> getClases(Long idEstudiante, Long idProfesor);

}
