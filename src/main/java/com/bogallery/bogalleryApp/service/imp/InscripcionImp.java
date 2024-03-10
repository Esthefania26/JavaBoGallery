package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Inscripcion;
import com.bogallery.bogalleryApp.repository.InscripcionRepository;
import com.bogallery.bogalleryApp.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionImp implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    public List<Inscripcion> findAll() throws Exception{
        return this.inscripcionRepository.findAll();

    }

    @Autowired
    public Inscripcion findById(int id){
        return this.inscripcionRepository.findById(id);


    }

    @Override
    public void create(Inscripcion inscripcion){
        this.inscripcionRepository.save(inscripcion);
    }

    @Override
    public void update(Inscripcion inscripcion){
        this.inscripcionRepository.save(inscripcion);
    }

    @Override
    public void delete(Inscripcion inscripcion){
        this.inscripcionRepository.save(inscripcion);
    }

}
