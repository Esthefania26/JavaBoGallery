package com.bogallery.bogalleryApp.service;



import com.bogallery.bogalleryApp.entities.Usuario;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {
    public List<Usuario> findAll() throws Exception;

    public Usuario findById(Long id);
    public  void create(Usuario usuario);

    public  void update(Usuario usuario);

    public  void delete(Usuario usuario);

}
