package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Novedad;
import com.bogallery.bogalleryApp.repository.NovedadRepository;
import com.bogallery.bogalleryApp.service.NovedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovedadImp implements NovedadService {

    @Autowired
    private NovedadRepository novedadRepository;

    @Override
    public List<Novedad> findAll() throws Exception {
        return this.novedadRepository.findAll();
    }

    // Este método no debería tener la anotación @Autowired
    public Novedad findById(Long id) {
        return this.novedadRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Novedad novedad) {
        this.novedadRepository.save(novedad);
    }

    @Override
    public void update(Novedad novedad) {
        this.novedadRepository.save(novedad);
    }

    @Override
    public void delete(Novedad novedad) {
        this.novedadRepository.delete(novedad);
    }
}
