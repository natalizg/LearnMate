package com.learnmate.learnmateback.usuarios.application.services;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ClaseDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ProfesorDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ProfesorFilter;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.UsuarioDto;
import com.learnmate.learnmateback.usuarios.adapter.out.repositories.*;
import com.learnmate.learnmateback.usuarios.application.domain.*;
import com.learnmate.learnmateback.usuarios.application.ports.in.IUsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private TramoHorarioRepository tramoHorarioRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private MateriaRepository materiaRepository;


    @Override
    public Usuario loginUsuario(String email, String password) {

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

    public List<ClaseDto> getAllClasesByIdProfesorOrIdEstudiante(Long idEstudiante, Long idProfesor) {

        List<Clase> clases = Optional.ofNullable(idEstudiante)
                .map(estudianteId -> claseRepository.findAllByEstudiante_IdEstudiante(estudianteId))
                .orElseGet(() -> Optional.ofNullable(idProfesor)
                        .map(profesorId -> claseRepository.findAllByProfesor_IdProfesor(profesorId))
                        .orElse(null));

        return clases.stream().map(this::convertClaseToDto).collect(Collectors.toList());

    }

    private ClaseDto convertClaseToDto(Clase clase) {
        ClaseDto dto = new ClaseDto();
        dto.setIdClase(clase.getIdClase());
        dto.setFecha(clase.getFecha());
        dto.setUsuarioEstudiante(clase.getEstudiante().getUsuario());
        dto.setUsuarioProfesor(clase.getProfesor().getUsuario());
        dto.setTramoHorario(clase.getTramoHorario());
        dto.setMateria(clase.getMateria());
        return dto;
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

            // Rescato los tramos horarios y se los asigno al nuevo profesor
            List<TramoHorario> tramosHorarios = tramoHorarioRepository.findAllById(usuario.getProfesor().getTramosHorarios().stream().map(TramoHorario::getIdTramoHorario).toList());
            profesor.setTramosHorarios(tramosHorarios);

            profesor.setUsuario(nuevoUsuario);
            nuevoUsuario.setProfesor(profesor);
        } else {
            Estudiante estudiante = new Estudiante();

            estudiante.setUsuario(nuevoUsuario);
            nuevoUsuario.setEstudiante(estudiante);
        }

        // Devuelvo el nuevo usuario creado
        return usuarioRepository.save(nuevoUsuario);
    }

    @Override
    public Usuario updateUsuario(UsuarioDto usuario, String password) {

        if (usuario.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID del usuario introducido no puede ser nulo");
        }

        // Verifico si el usuario existe e intento rescatarlo
        Usuario actualizarUsuario = usuarioRepository.findById(usuario.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("No existe ningun usuario con el ID: " + usuario.getIdUsuario()));

        // Mapeao los cambios desde el usuario recibido al almacenado en la bbdd existente
        modelMapper.map(usuario, actualizarUsuario);
        actualizarUsuario.setPassword(password);

        // Verifico si el usuario es un profesor
        if (usuario.getProfesor() != null) {
            Profesor profesor = modelMapper.map(usuario.getProfesor(), Profesor.class);

            // Rescato los tramos horarios y se los asigno al profesor
            List<TramoHorario> tramosHorarios = tramoHorarioRepository.findAllById(
                    usuario.getProfesor().getTramosHorarios().stream().map(TramoHorario::getIdTramoHorario).toList()
            );
            profesor.setTramosHorarios(tramosHorarios);

            profesor.setUsuario(actualizarUsuario);
            actualizarUsuario.setProfesor(profesor);
        } else {
            Estudiante estudiante = modelMapper.map(usuario.getEstudiante(), Estudiante.class);

            estudiante.setUsuario(actualizarUsuario);
            actualizarUsuario.setEstudiante(estudiante);
        }

        // Guardo los cambios y devuelvo el usuario actualizado
        return usuarioRepository.save(actualizarUsuario);
    }

    @Override
    public Usuario setFotoUsuario(Long idUsuario, MultipartFile foto) throws IOException {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException("el id del usuario introducido no existe"));

        usuario.setFoto(foto.getBytes());

        return usuarioRepository.save(usuario);
    }

    @Override
    public ClaseDto createClase(ClaseDto clase) {

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
        return convertClaseToDto(claseRepository.save(nuevaClase));
    }

    @Override
    public ClaseDto updateClase(ClaseDto clase) {

        if (clase.getIdClase() == null) {
            throw new IllegalArgumentException("El ID de la clase introducida no puede ser nulo");
        }

        // Recupera la clase existente desde el repositorio
        Clase actualizarClase = claseRepository.findById(clase.getIdClase())
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna clase con el ID: " + clase.getIdClase()));

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
        return convertClaseToDto(claseRepository.save(actualizarClase));
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

    @Override
    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    @Override
    public List<TramoHorario> getAllTramosHorarios() {
        return tramoHorarioRepository.findAll();
    }

    @Override
    public Page<UsuarioDto> getAllProfesores(ProfesorFilter filter, Pageable pageable) {

        Specification<Profesor> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getNombre() != null) {
                String nombreLower = "%" + filter.getNombre().toLowerCase() + "%";

                Predicate nombrePredicate = cb.like(cb.lower(root.get("usuario").get("nombre")), nombreLower);
                Predicate apellidosPredicate = cb.like(cb.lower(root.get("usuario").get("apellidos")), nombreLower);

                predicates.add(cb.or(nombrePredicate, apellidosPredicate));
            }
            if (filter.getPrecioMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("precio"), filter.getPrecioMin()));
            }
            if (filter.getPrecioMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("precio"), filter.getPrecioMax()));
            }
            if (filter.getIdMateria() != null) {
                predicates.add(cb.equal(root.get("materia").get("idMateria"), filter.getIdMateria()));
            }
            if (filter.getIdsTramosHorarios() != null && !filter.getIdsTramosHorarios().isEmpty()) {
                Join<Profesor, TramoHorario> join = root.join("tramosHorarios", JoinType.INNER);

                predicates.add(join.get("idTramoHorario").in(filter.getIdsTramosHorarios()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Profesor> profesores = profesorRepository.findAll(spec, pageable);

        return profesores.map(profesor -> {
            UsuarioDto usuarioDto = modelMapper.map(profesor.getUsuario(), UsuarioDto.class);
            ProfesorDto profesorDto = modelMapper.map(profesor, ProfesorDto.class);
            usuarioDto.setProfesor(profesorDto);
            return usuarioDto;
        });
    }

}
