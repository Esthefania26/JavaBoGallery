package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Rol;

import java.util.List;
import java.util.Optional;
public interface RolService {

    public List<Rol> findAll() throws Exception;

    public Rol findById(Long id);

    public void create(Rol rol);
    public void update(Rol rol);
    public void delete(Rol rol);

}
