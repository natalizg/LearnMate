package com.learnmate.learnmateback.usuarios.application.ports.in;

import com.learnmate.learnmateback.usuarios.application.domain.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> getAllUsuarios();

}
