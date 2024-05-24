package com.learnmate.learnmateback.usuarios.application.services;

import com.learnmate.learnmateback.usuarios.adapter.out.repositories.ClaseRepository;
import com.learnmate.learnmateback.usuarios.adapter.out.repositories.UsuarioRepository;
import com.learnmate.learnmateback.usuarios.application.domain.Clase;
import com.learnmate.learnmateback.usuarios.application.domain.Estudiante;
import com.learnmate.learnmateback.usuarios.application.domain.Profesor;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
import com.learnmate.learnmateback.usuarios.application.ports.in.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public Usuario loginUser(String email, String password) {

        // Intento rescatar al usuario mediante el email
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Compruebo si he logrado rescatar al usurio
        if (usuario == null) {
            // Si no existe ningun usuario registrado con el email recibido, devuelvo una excepción
            throw new IllegalArgumentException("No existe usuario registrado con el email introducido");
        }

        // Si consigo rescatar el usuario compruebo que la contraseña almacenada coincide con la introducida
        if (!usuario.getPassword().equals(password)) {
            // Si la contraseña no coincide devuelvo una excepción indicándolo
            throw new IllegalArgumentException("La contraseña introducida es erronea");
        }

        //Si existe un usuario registrado con el email introducido y la contraseña es correcta, devuelvo el usuario
        return usuario;
    }

    public List<Usuario> getAllAlumnosByidProfesor(Long idProfesor) {

        return claseRepository.findAllByProfesor_IdProfesor(idProfesor).stream().map(Clase::getEstudiante).toList()
                .stream().map(Estudiante::getUsuario).toList();
    }

    public List<Usuario> getAllProfesoresByIdEstudiante(Long idEstudiante) {

        return claseRepository.findAllByEstudiante_IdEstudiante(idEstudiante).stream().map(Clase::getProfesor).toList()
                .stream().map(Profesor::getUsuario).toList();
    }

    public List<Clase> getAllClasesByIdProfesorOrIdEstudiante(Long idEstudiante, Long idProfesor) {

        return Optional.ofNullable(idEstudiante)
                .map(estudianteId -> claseRepository.findAllByEstudiante_IdEstudiante(estudianteId))
                .orElseGet(() -> Optional.ofNullable(idProfesor)
                        .map(profesorId -> claseRepository.findAllByProfesor_IdProfesor(profesorId))
                        .orElse(null));
    }

}
