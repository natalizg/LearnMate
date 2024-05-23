package com.learnmate.learnmateback.usuarios.application.services;

import com.learnmate.learnmateback.usuarios.adapter.out.repositories.UsuarioRepository;
import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
import com.learnmate.learnmateback.usuarios.application.ports.in.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
