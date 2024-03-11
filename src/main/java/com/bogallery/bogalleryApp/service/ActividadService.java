package com.bogallery.bogalleryApp.service;

import com.bogallery.bogalleryApp.entities.Actividad;

import java.util.List;
import java.util.Optional;
public interface ActividadService {


    public List<Actividad> findAll() throws Exception;

    public Actividad findById(int id);
    public  void create(Actividad actividad);

    public  void update(Actividad actividad);

    public  void delete(Actividad actividad);
}
