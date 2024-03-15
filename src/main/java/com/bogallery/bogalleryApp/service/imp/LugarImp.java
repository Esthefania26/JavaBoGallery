package com.bogallery.bogalleryApp.service.imp;

import com.bogallery.bogalleryApp.entities.Lugar;
import com.bogallery.bogalleryApp.repository.LugarRepository;
import com.bogallery.bogalleryApp.service.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LugarImp implements LugarService {
    @Autowired
    private LugarRepository lugarRepository;

    @Override
    public List<Lugar> findAll() throws  Exception
    {
        return this.lugarRepository.findAll();
    }

    @Override
    public Lugar findById(Long id)
    {
        return this.lugarRepository.findById(id).orElse(null);
    }

    @Override

    public void create(Lugar lugar)
    {
        this.lugarRepository.save(lugar);
    }
    @Override

    public void update(Lugar lugar)
    {
        this.lugarRepository.save(lugar);
    }
    @Override

    public void delete(Lugar lugar)
    {
        this.lugarRepository.delete(lugar);
    }

}
