package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Novedad;

import java.util.List;
import java.util.Optional;

public interface NovedadService {

    public List<Novedad> findAll() throws Exception;

    public Novedad findById(Long id);

    public void create(Novedad novedad);
    public void update(Novedad novedad);
    public void delete(Novedad novedad);

}
