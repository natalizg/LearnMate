package com.learnmate.learnmateback.usuarios.adapter.in.rest;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.*;
import com.learnmate.learnmateback.usuarios.application.ports.in.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Servicio de logeo en la aplicación
     *
     * @param email
     * @param password
     * @return Usuario
     */
    @Operation(summary = "Logea al usuario", description = "Este método se ultiliza para comprobar que existen usuarios registrados con el email introducido " +
            "y si la contraseña introducida es correcta. Si ambas condiciones se cumplen devuelve los datos del usuario",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/loginUsuario")
    public ResponseEntity<UsuarioDto> loginUsuario(@RequestParam("email") String email,
                                                @RequestParam("password") String password) {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.loginUsuario(email, password), UsuarioDto.class);

        return new ResponseEntity<>(usuarioOut, HttpStatus.OK);
    }

    /**
     * Servicio que devuelve todos los estudiantes de un profesor
     *
     * @param idProfesor
     * @return List<UsuarioDto>
     */
    @Operation(summary = "Devuelve los estudiantes de un profesor", description = "Este método se ultiliza para recuperar los datos de todos los estudiantes de un profesor",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllEstudiantesByidProfesor")
    public ResponseEntity<List<UsuarioDto>> getAllEstudiantesByidProfesor(@RequestParam("idProfesor") Long idProfesor) {

        List<UsuarioDto> usuariosOut = usuarioService.getAllEstudiantesByidProfesor(idProfesor).stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(usuariosOut, HttpStatus.OK);
    }

    /**
     * Servicio que devuelve todos los estudiantes de un profesor
     *
     * @param idEstudiante
     * @return List<UsuarioDto>
     */
    @Operation(summary = "Devuelve los profesores de un estudiante", description = "Este método se ultiliza para recuperar los datos de todos los profesores de un estudiante",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllProfesoresByIdEstudiante")
    public ResponseEntity<List<UsuarioDto>> getAllProfesoresByIdEstudiante(@RequestParam("idEstudiante") Long idEstudiante) {

        List<UsuarioDto> usuariosOut = usuarioService.getAllProfesoresByIdEstudiante(idEstudiante).stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(usuariosOut, HttpStatus.OK);
    }

    /**
     * Servicio que devuelve todas las clases que tenga un profesor o un estudiante
     *
     * @param idEstudiante
     * @param idProfesor
     * @return List<ClaseDto>
     */
    @Operation(summary = "Devuelve los profesores de un estudiante", description = "Este método se ultiliza para recuperar los de "+
            "datos todas las clases que tenga un profesor o un estudiante",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllClasesByIdProfesorOrIdEstudiante")
    public ResponseEntity<List<ClaseDto>> getAllClasesByIdProfesorOrIdEstudiante(@RequestParam(value = "idEstudiante", required = false) Long idEstudiante,
                                                                                 @RequestParam(value = "idProfesor", required = false) Long idProfesor) {

        return new ResponseEntity<>(usuarioService.getAllClasesByIdProfesorOrIdEstudiante(idEstudiante, idProfesor), HttpStatus.OK);
    }

    /**
     * Servicio que crea un profesor o un estudiante y lo devuelve
     *
     * @param usuario
     * @param password
     * @return UsuarioDto
     */
    @Operation(summary = "Crea profesores y estudiante", description = "Este método se ultiliza para crea un profesor o un estudiante y posteriormente lo devuelve",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @PostMapping("/createUsuario")
    public ResponseEntity<UsuarioDto> createUsuario(@RequestParam("password") String password,
                                                    @RequestBody UsuarioDto usuario) {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.createUsuario(usuario, password), UsuarioDto.class);

        return new ResponseEntity<>(usuarioOut, HttpStatus.CREATED);
    }

    /**
     * Servicio que actgualiza los datos de un profesor o un estudiante y lo devuelve
     *
     * @param usuario
     * @param password
     * @return UsuarioDto
     */
    @Operation(summary = "Crea profesores y estudiante", description = "Este método se ultiliza para ctualizar los datos " +
            "de un profesor o un estudiante y posteriormente lo devuelve",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @PutMapping("/updateUsuario")
    public ResponseEntity<UsuarioDto> updateUsuario(@RequestParam("password") String password,
                                                    @RequestBody UsuarioDto usuario) {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.updateUsuario(usuario, password), UsuarioDto.class);

        return new ResponseEntity<>(usuarioOut, HttpStatus.CREATED);
    }

    /**
     * Servicio que actualiza la foto de un usuario
     *
     * @param idUsuario
     * @param foto
     * @return UsuarioDto
     */
    @Operation(summary = "Actualiza la foto de un usuario", description = "Este método se ultiliza para crea o actualizar la foto de un usuario",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @PutMapping("/setFotoUsuario")
    public ResponseEntity<UsuarioDto> setFotoUsuario(@RequestParam("idUsuario") Long idUsuario,
                                                     @RequestBody MultipartFile foto) throws IOException {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.setFotoUsuario(idUsuario, foto), UsuarioDto.class);

        return new ResponseEntity<>(usuarioOut, HttpStatus.OK);
    }

    /**
     * Servicio que crea una clase y la devuelve
     *
     * @param clase
     * @return ClaseDto
     */
    @Operation(summary = "Crea profesores y estudiante", description = "Este método se ultiliza para crear una clase y la devolverla",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @PostMapping("/createClase")
    public ResponseEntity<ClaseDto> createClase(@RequestBody ClaseDto clase) {

        return new ResponseEntity<>(usuarioService.createClase(clase), HttpStatus.CREATED);
    }

    /**
     * Servicio que actualiza los datos de una clase y la devuelve
     *
     * @param clase
     * @return ClaseDto
     */
    @Operation(summary = "Crea profesores y estudiante", description = "Este método se ultiliza para actualizar los datos de una clase devolverla",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @PutMapping("/updateClase")
    public ResponseEntity<ClaseDto> updateClase(@RequestBody ClaseDto clase) {

        return new ResponseEntity<>(usuarioService.updateClase(clase), HttpStatus.OK);
    }

    /**
     * Servicio para borrar una clase
     *
     * @param idClase
     * @return HttpStatus
     */
    @Operation(summary = "Crea profesores y estudiante", description = "Este método se ultiliza para para borrar una clase",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @DeleteMapping("/deleteClase")
    public ResponseEntity<HttpStatus> deleteClase(@RequestParam("idClase") Long idClase) {

        usuarioService.deleteClase(idClase);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Servicio para rescatar todas las materias
     *
     * @return List<MateriaDto>
     */
    @Operation(summary = "Rescata todas las materias", description = "Este método se ultiliza para rescatar todas las materias",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllMaterias")
    public ResponseEntity<List<MateriaDto>> getAllMaterias() {

        List<MateriaDto> materiasOut = usuarioService.getAllMaterias().stream()
                .map(materia -> modelMapper.map(materia, MateriaDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(materiasOut, HttpStatus.OK);
    }

    /**
     * Servicio para rescatar todos los tramos horarios
     *
     * @return List<TramoHorarioDto>
     */
    @Operation(summary = "Rescata todos los tramos horarios", description = "Este método se ultiliza para rescatar todos los tramos horarios",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllTramosHorarios")
    public ResponseEntity<List<TramoHorarioDto>> getAllTramosHorarios() {

        List<TramoHorarioDto> tramoHorarioOut = usuarioService.getAllTramosHorarios().stream()
                .map(tramoHorario -> modelMapper.map(tramoHorario, TramoHorarioDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(tramoHorarioOut, HttpStatus.OK);
    }

    /**
     * Servicio para rescatar todos los profesores en función de los filtros recibidos
     *
     * @param nombre
     * @param precioMin
     * @param precioMax
     * @param idMateria
     * @param idsTramosHorarios
     * @param page
     * @param size
     * @return Page<UsuarioDto>
     */
    @Operation(summary = "Rescata todos los profesores", description = "Este método se ultiliza para rescatar todos los profesores en función " +
            "de los filtros recibidos",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllProfesores")
    public ResponseEntity<Page<UsuarioDto>> getAllProfesores(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer precioMin,
            @RequestParam(required = false) Integer precioMax,
            @RequestParam(required = false) Long idMateria,
            @RequestParam(required = false) List<Long> idsTramosHorarios,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        ProfesorFilter filter = new ProfesorFilter();
        filter.setNombre(nombre);
        filter.setPrecioMin(precioMin);
        filter.setPrecioMax(precioMax);
        filter.setIdMateria(idMateria);
        filter.setIdsTramosHorarios(idsTramosHorarios);

        Page<UsuarioDto> profesoresOut = usuarioService.getAllProfesores(filter, pageable);

        return new ResponseEntity<>(profesoresOut, HttpStatus.OK);
    }

}
