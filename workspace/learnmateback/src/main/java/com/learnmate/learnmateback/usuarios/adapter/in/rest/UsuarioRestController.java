package com.learnmate.learnmateback.usuarios.adapter.in.rest;

import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ClaseDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.EstudianteDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.ProfesorDto;
import com.learnmate.learnmateback.usuarios.adapter.in.rest.model.UsuarioDto;
import com.learnmate.learnmateback.usuarios.application.domain.Estudiante;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
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
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/loginUser")
    public ResponseEntity<UsuarioDto> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {

        UsuarioDto usuarioOut = modelMapper.map(usuarioService.loginUser(email, password), UsuarioDto.class);

        return new ResponseEntity<>(usuarioOut, HttpStatus.OK);
    }

    /**
     * Servicio que devuelve todos los estudiantes de un profesor
     *
     * @param idProfesor
     * @return List<UsuarioDto>
     */
    @Operation(summary = "Devuelve los estudiantes de un profesor", description = "Este método se ultiliza para recuperar los datos de todos los alumnos de un profesor",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllAlumnosByidProfesor")
    public ResponseEntity<List<UsuarioDto>> getAllAlumnosByidProfesor(@RequestParam("idProfesor") Long idProfesor) {

        List<UsuarioDto> usuariosOut = usuarioService.getAllAlumnosByidProfesor(idProfesor).stream()
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

}
