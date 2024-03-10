package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Rol;
import com.bogallery.bogalleryApp.repository.RolRepository;
import com.bogallery.bogalleryApp.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
public class RolImp implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> findAll() throws Exception
    {
        return this.rolRepository.findAll();
    }
    @Override

    public Rol findById(int id)
    {
        return this.rolRepository.findById(id);
    }

    @Override
    public void create(Rol rol)
    {
        this.rolRepository.save(rol);
    }
    @Override
    public void update(Rol rol)
    {
        this.rolRepository.save(rol);
    }
    @Override
    public void delete(Rol rol)
    {
        this.rolRepository.delete(rol);
    }
}
