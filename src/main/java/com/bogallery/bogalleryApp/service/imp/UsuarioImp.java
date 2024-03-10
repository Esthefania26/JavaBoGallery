package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Usuario;
import com.bogallery.bogalleryApp.repository.UsuarioRepository;
import com.bogallery.bogalleryApp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UsuarioImp implements UsuarioService {

    @Autowired

    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() throws  Exception{
        return this.usuarioRepository.findAll();
    }
    @Override
    public Usuario findById(int id)
    {
        return this.usuarioRepository.findById(id);
    }

    @Override

    public  void create(Usuario usuario)
    {
        this.usuarioRepository.save(usuario);
    }

    @Override

    public  void update(Usuario usuario)
    {
        this.usuarioRepository.save(usuario);
    }
    @Override

    public  void delete(Usuario usuario)
    {
        this.usuarioRepository.save(usuario);
    }


}
