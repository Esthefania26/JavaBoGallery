package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Promocion;
import com.bogallery.bogalleryApp.repository.PromocionRepository;
import com.bogallery.bogalleryApp.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionImp implements PromocionService {
    @Autowired

    private PromocionRepository promocionRepository;

    @Override
    public List<Promocion> findAll() throws Exception {
        return this.promocionRepository.findAll();

    }

    @Autowired
    public Promocion findById(int id) {
        return this.promocionRepository.findById(id);


    }
    @Override
    public void create(Promocion promocion){
        this.promocionRepository.save(promocion);
    }

    @Override
    public void update(Promocion promocion){
        this.promocionRepository.save(promocion);
    }

    @Override
    public void delete(Promocion promocion){
        this.promocionRepository.save(promocion);
    }




}
