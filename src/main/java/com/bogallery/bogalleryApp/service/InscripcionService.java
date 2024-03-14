package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Inscripcion;

import java.util.List;
import java.util.Optional;



public interface InscripcionService {

    public List<Inscripcion> findAll() throws Exception;

    public Inscripcion findById(Long id);

    public void create(Inscripcion inscripcion);
    public void update(Inscripcion inscripcion);
    public void  delete(Inscripcion inscripcion);
}
