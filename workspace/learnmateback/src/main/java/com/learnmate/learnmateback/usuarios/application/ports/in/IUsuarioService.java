package com.learnmate.learnmateback.usuarios.application.ports.in;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ClaseDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.UsuarioDto;
import com.learnmate.learnmateback.usuarios.application.domain.Clase;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario loginUser(String email, String password);

    List<Usuario> getAllEstudiantesByidProfesor(Long idProfesor);

    List<Usuario> getAllProfesoresByIdEstudiante(Long idEstudiante);

    List<Clase> getAllClasesByIdProfesorOrIdEstudiante(Long idEstudiante, Long idProfesor);

    Usuario createUsuario(UsuarioDto usuario, String password);

    Usuario updateUsuario(UsuarioDto usuario, String password);

    Clase createClase(ClaseDto clase);

    Clase updateClase(ClaseDto clase);

    void deleteClase(Long idClase);

}
