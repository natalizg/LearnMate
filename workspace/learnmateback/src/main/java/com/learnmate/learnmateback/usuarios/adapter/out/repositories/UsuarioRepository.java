package com.learnmate.learnmateback.usuarios.adapter.out.repositories;

import com.learnmate.learnmateback.usuarios.application.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}
