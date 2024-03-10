package com.bogallery.bogalleryApp.repository;


import com.bogallery.bogalleryApp.entities.Audio_Relato;
import com.bogallery.bogalleryApp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findById(int id);
}
