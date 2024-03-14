package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Actividad;
import com.bogallery.bogalleryApp.repository.ActividadesRepository;
import com.bogallery.bogalleryApp.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service

public class ActividadImp implements ActividadService {
    @Autowired
    private ActividadesRepository actividadesRepository;
    @Override

    public List<Actividad> findAll() throws Exception
    {
        return this.actividadesRepository.findAll();
    }
    @Override
    public Actividad findById(Long id)
    {
        return this.actividadesRepository.findById(id).orElse(null);
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
        this.actividadesRepository.delete(actividad);
    }

}
