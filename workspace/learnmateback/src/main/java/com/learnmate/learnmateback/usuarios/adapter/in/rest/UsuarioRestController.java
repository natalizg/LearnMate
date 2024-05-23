package com.learnmate.learnmateback.usuarios.adapter.in.rest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    ModelMapper modelMapper;

    @Operation(summary = "Recuperar todos los usuarios", description = "Este método se ultiliza para recuperar todos los usuarios de la base de datos",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK, obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para realizar esta operacion"),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado")})
    @GetMapping("/getAllUsuarios")
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {

        List<UsuarioDto> usuariosOut = usuarioService.getAllUsuarios().stream().map(x -> modelMapper.map(x, UsuarioDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(usuariosOut, HttpStatus.OK);
    }

}
