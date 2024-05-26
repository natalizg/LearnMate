package com.learnmate.learnmateback.usuarios.adapter.in.rest;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ClaseDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.UsuarioDto;
import com.learnmate.learnmateback.usuarios.application.ports.in.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/loginUser")
    public ResponseEntity<UsuarioDto> loginUser(@RequestParam("email") String email,
                                                @RequestParam("password") String password) {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.loginUser(email, password), UsuarioDto.class);

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

        List<ClaseDto> clasesOut = usuarioService.getAllClasesByIdProfesorOrIdEstudiante(idEstudiante, idProfesor).stream()
                .map(clase -> modelMapper.map(clase, ClaseDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(clasesOut, HttpStatus.OK);
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
    @PostMapping("/updateUsuario")
    public ResponseEntity<UsuarioDto> updateUsuario(@RequestParam("password") String password,
                                                    @RequestBody UsuarioDto usuario) {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.updateUsuario(usuario, password), UsuarioDto.class);

        return new ResponseEntity<>(usuarioOut, HttpStatus.CREATED);
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

        ClaseDto claseOut = modelMapper.map(usuarioService.createClase(clase), ClaseDto.class);

        return new ResponseEntity<>(claseOut, HttpStatus.CREATED);
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

        ClaseDto claseOut = modelMapper.map(usuarioService.updateClase(clase), ClaseDto.class);

        return new ResponseEntity<>(claseOut, HttpStatus.OK);
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

}
