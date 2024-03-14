package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Rol;
import com.bogallery.bogalleryApp.repository.RolRepository;
import com.bogallery.bogalleryApp.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolImp implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> findAll() throws Exception
    {
        return this.rolRepository.findAll();
    }
    @Override

    public Rol findById(Long id)
    {
        return this.rolRepository.findById(id).orElse(null);

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
