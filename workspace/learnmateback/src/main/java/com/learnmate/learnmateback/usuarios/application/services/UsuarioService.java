package com.learnmate.learnmateback.usuarios.application.services;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ClaseDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.UsuarioDto;
import com.learnmate.learnmateback.usuarios.adapter.out.repositories.*;
import com.learnmate.learnmateback.usuarios.application.domain.*;
import com.learnmate.learnmateback.usuarios.application.ports.in.IUsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TramoHorarioRepository tramoHorarioRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private MateriaRepository materiaRepository;

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

    public List<Usuario> getAllEstudiantesByidProfesor(Long idProfesor) {

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

    @Override
    public Usuario createUsuario(UsuarioDto usuario, String password) {

        // Compruebo si el correo introducido ya está registrado por otro usuario
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("El correo introducido ya está registrado");
        }

        Usuario nuevoUsuario = modelMapper.map(usuario, Usuario.class);
        nuevoUsuario.setPassword(password);

        // Compruebo si es un profesor o estudiante
        if (usuario.getProfesor() != null) {
            Profesor profesor = modelMapper.map(usuario.getProfesor(), Profesor.class);

            //Rescato los tramos horarios y se los seteo al nuevo profesor
            List<TramoHorario> tramosHorarios = tramoHorarioRepository.findAllById(usuario.getProfesor().getTramosHorarios().stream().map(TramoHorario::getIdTramoHorario).toList());
            profesor.setTramosHorarios(tramosHorarios);

            profesor.setUsuario(nuevoUsuario);
            nuevoUsuario.setProfesor(profesor);
        } else {
            Estudiante estudiante = new Estudiante();

            estudiante.setUsuario(nuevoUsuario);
            nuevoUsuario.setEstudiante(estudiante);
        }

        //devuelvo el nuevo usuario creado
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Usuario updateUsuario(UsuarioDto usuario, String password) {

        // Compruebo si el correo introducido ya está registrado por otro usuario
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("El correo introducido ya está registrado");
        } 

        Usuario nuevoUsuario = modelMapper.map(usuario, Usuario.class);
        nuevoUsuario.setPassword(password);

        // Compruebo si es un profesor o estudiante
        if (usuario.getProfesor() != null) {
            Profesor profesor = modelMapper.map(usuario.getProfesor(), Profesor.class);

            //Rescato los tramos horarios y se los seteo al nuevo profesor
            List<TramoHorario> tramosHorarios = tramoHorarioRepository.findAllById(usuario.getProfesor().getTramosHorarios().stream().map(TramoHorario::getIdTramoHorario).toList());
            profesor.setTramosHorarios(tramosHorarios);

            profesor.setUsuario(nuevoUsuario);
            nuevoUsuario.setProfesor(profesor);
        } else {
            Estudiante estudiante = new Estudiante();

            estudiante.setUsuario(nuevoUsuario);
            nuevoUsuario.setEstudiante(estudiante);
        }

        //devuelvo el nuevo usuario creado
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Clase createClase(ClaseDto clase) {

        Clase nuevaClase = modelMapper.map(clase, Clase.class);

        // Rescato las entidades con los datos que trae el Dto
        Estudiante estudiante = estudianteRepository.findById(clase.getEstudiante().getIdEstudiante())
                .orElseThrow(() -> new EntityNotFoundException("El estudiante introducido no existe"));
        Profesor profesor = profesorRepository.findById(clase.getProfesor().getIdProfesor())
                .orElseThrow(() -> new EntityNotFoundException("El profesor introducido no existe"));
        TramoHorario tramoHorario = tramoHorarioRepository.findById(clase.getTramoHorario().getIdTramoHorario())
                .orElseThrow(() -> new EntityNotFoundException("El tramo horario seleccionado no existe"));
        Materia materia = materiaRepository.findById(clase.getMateria().getIdMateria())
                .orElseThrow(() -> new EntityNotFoundException("La materia seleccionada no existe"));

        // Asigno las entidades rescatadas a la nueva Clase
        nuevaClase.setEstudiante(estudiante);
        nuevaClase.setProfesor(profesor);
        nuevaClase.setTramoHorario(tramoHorario);
        nuevaClase.setMateria(materia);

        // Creo y devuelvo la nueva clase
        return claseRepository.save(nuevaClase);
    }

    @Override
    public Clase updateClase(ClaseDto clase) {

        if (clase.getIdClase() == null) {
            throw new IllegalArgumentException("El ID de la clase introducida no puede ser nulo");
        }

        // Recupera la clase existente desde el repositorio
        Clase actualizarClase = claseRepository.findById(clase.getIdClase())
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna clase con el ID: " + clase.getIdClase()));

        Clase nuevaClase = modelMapper.map(clase, Clase.class);

        // Rescato las entidades con los datos que trae el Dto
        Estudiante estudiante = estudianteRepository.findById(clase.getEstudiante().getIdEstudiante())
                .orElseThrow(() -> new EntityNotFoundException("El estudiante introducido no existe"));
        Profesor profesor = profesorRepository.findById(clase.getProfesor().getIdProfesor())
                .orElseThrow(() -> new EntityNotFoundException("El profesor introducido no existe"));
        TramoHorario tramoHorario = tramoHorarioRepository.findById(clase.getTramoHorario().getIdTramoHorario())
                .orElseThrow(() -> new EntityNotFoundException("El tramo horario seleccionado no existe"));
        Materia materia = materiaRepository.findById(clase.getMateria().getIdMateria())
                .orElseThrow(() -> new EntityNotFoundException("La materia seleccionada no existe"));

        // Asigno las entidades rescatadas a la nueva Clase
        actualizarClase.setEstudiante(estudiante);
        actualizarClase.setProfesor(profesor);
        actualizarClase.setTramoHorario(tramoHorario);
        actualizarClase.setMateria(materia);

        // Creo y devuelvo la nueva clase
        return claseRepository.save(actualizarClase);
    }

    @Override
    public void deleteClase(Long idClase) {

        Optional<Clase> clase = claseRepository.findById(idClase);

        if (clase.isPresent()) {
            claseRepository.delete(clase.get());
        } else {
            throw new EntityNotFoundException("La clase con id " + idClase + " no existe");
        }
    }

}
