package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Actividad;
import com.bogallery.bogalleryApp.repository.ActividadesRepository;
import com.bogallery.bogalleryApp.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ActividadImp implements ActividadService {
    @Autowired
    private ActividadesRepository actividadesRepository;
    @Override

    public List<Actividad> findAll() throws Exception
    {
        return this.actividadesRepository.findAll();
    }
    @Override
    public Actividad findById(int id)
    {
        return this.actividadesRepository.findById(id);
    }
    @Override
    public void create(Actividad actividad)
    {
        this.actividadesRepository.save(actividad);
    }
    @Override
    public void update(Actividad actividad)
    {
        this.actividadesRepository.save(actividad);
    }

    @Override
    public void delete(Actividad actividad)
    {
        this.actividadesRepository.save(actividad);
    }

}
